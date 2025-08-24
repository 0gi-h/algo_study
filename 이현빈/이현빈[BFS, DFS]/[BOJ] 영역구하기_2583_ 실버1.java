import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int M, N, K, count;
    static boolean[][] mapArr;
    static PriorityQueue<Integer> areaExtentPQ;

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception
    {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        count = 0;

        areaExtentPQ = new PriorityQueue<>();

        mapArr = new boolean[N][M];

        for (int i = 0; i < K; i++)
        {
            st = new StringTokenizer(br.readLine());

            int sc = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());

            for (int j = sr; j < er; j++)
            {
                for (int k = sc; k < ec; k++)
                    mapArr[j][k] = true;
            }
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if(!mapArr[i][j])
                {
                    bfs(i, j);
                    count++;
                }
            }
        }
        sb.append(count).append("\n");
        while (!areaExtentPQ.isEmpty())
            sb.append(areaExtentPQ.poll()).append(" ");
        System.out.println(sb);
    }

    private static void bfs(int x, int y)
    {
        Deque<int[]> bfsQ = new ArrayDeque<>();
        bfsQ.offer(new int[]{x,y});

        int extent = 0;

        while (!bfsQ.isEmpty())
        {
            int[] pos = bfsQ.poll();

            for (int i = 0; i < dx.length; i++)
            {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !mapArr[nx][ny])
                {
                    mapArr[nx][ny] = true;
                    bfsQ.offer(new int[]{nx, ny});
                    extent++;
                }
            }
        }
        areaExtentPQ.offer(extent == 0 ? 1 : extent);
    }

    static void PrintArr()
    {
        for (int i = 0; i < N; i++)
            System.out.println(Arrays.toString(mapArr[i]));
    }
}
