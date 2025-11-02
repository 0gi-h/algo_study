import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Set<Integer> resSet;
    static boolean[][][] visited;

    static int A, B, C;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        resSet = new HashSet<>();
        visited = new boolean[A+1][B+1][C+1];

        bfs();

        List<Integer> resList = new ArrayList<>(resSet);

        Collections.sort(resList);

        for(Integer n : resList)
            sb.append(n).append(" ");

        System.out.println(sb);
    }

    private static void bfs()
    {
        Deque<int[]> bfsQ = new ArrayDeque<>();
        bfsQ.offer(new int[]{0, 0, C});
        visited[0][0][C] = true;

        while (!bfsQ.isEmpty())
        {
            int[] cur = bfsQ.poll();

            int a = cur[0];
            int b = cur[1];
            int c = cur[2];

            if (a == 0)
                resSet.add(c);

            int next = 0;

            // c에서 b로 물을 부어봐
            if (c > 0)
            {
                next = Math.min(c, B - b);
                if (!visited[a][b + next][c - next])
                {
                    visited[a][b + next][c - next] = true;
                    bfsQ.offer(new int[]{a, b + next, c - next});
                }
            }

            // c에서 a로 물을 부어봐
            if (c > 0)
            {
                next = Math.min(c, A - a);
                if (!visited[a + next][b][c - next])
                {
                    visited[a + next][b][c - next] = true;
                    bfsQ.offer(new int[]{a + next, b, c - next});
                }
            }

            // b에서 c로 물을 부어봐
            if (b > 0)
            {
                next = Math.min(b, C - c);
                if (!visited[a][b - next][c + next])
                {
                    visited[a][b - next][c + next] = true;
                    bfsQ.offer(new int[]{a, b - next, c + next});
                }
            }

            // b에서 a로 물을 부어봐
            if (b > 0)
            {
                next = Math.min(b, A - a);
                if (!visited[a + next][b - next][c])
                {
                    visited[a + next][b - next][c] = true;
                    bfsQ.offer(new int[]{a + next, b - next, c});
                }
            }

            // a에서 b로 물을 부어봐
            if(a > 0)
            {
                next = Math.min(a, B - b);
                if (!visited[a - next][b + next][c])
                {
                    visited[a - next][b + next][c] = true;
                    bfsQ.offer(new int[]{a - next, b + next, c});
                }
            }

            // a에서 c로 물을 부어봐
            if(a > 0)
            {
                next = Math.min(a, C - c);
                if (!visited[a - next][b][c + next])
                {
                    visited[a - next][b][c + next] = true;
                    bfsQ.offer(new int[]{a - next, b, c + next});
                }
            }
        }
    }
}
