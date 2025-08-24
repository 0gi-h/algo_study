import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int F, S, G, U, D;

    static Deque<Integer> bfsQ;
    static int[] countArr;

    public static void main(String[] args) throws Exception
    {
        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        countArr = new int[F + 1];
        Arrays.fill(countArr, -1);

        bfs();
        System.out.println(countArr[G] == -1 ? "use the stairs" : countArr[G]);
    }

    private static void bfs()
    {
        bfsQ = new ArrayDeque<>();
        bfsQ.offer(S);
        countArr[S] = 0;

        while (!bfsQ.isEmpty())
        {
            int cur = bfsQ.poll();

            if (cur == G)
                return;

            // 위
            if (U != 0 && cur + U <= F && countArr[cur + U] == -1)
            {
                bfsQ.offer(cur + U);
                countArr[cur + U] = 1 + countArr[cur];
            }

            // 아래
            if (D != 0 && cur - D >= 1 && countArr[cur - D] == -1)
            {
                bfsQ.offer(cur - D);
                countArr[cur - D] = 1 + countArr[cur];
            }
        }
    }
}
