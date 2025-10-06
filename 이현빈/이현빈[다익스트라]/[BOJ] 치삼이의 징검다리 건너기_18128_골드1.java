import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Ground
    {
        int x, y, v, waterTime;

        public Ground(int x, int y, int v)
        {
            this.x = x;
            this.y = y;
            this.v = v;
            waterTime = Integer.MAX_VALUE;
        }
    }

    static int N, W;
    static Ground[][] groundArr;
    static int[] mdx = {-1, 1, 0, 0, -1, -1, 1, 1}; // move dx
    static int[] mdy = {0, 0, -1, 1, -1, 1, -1, 1};
    static int[] wdx = {-1, 1, 0, 0}; // water dx
    static int[] wdy = {0, 0, -1, 1};
    static List<int[]> waterPosList;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        waterPosList = new ArrayList<>();

        // 물
        for (int i = 0; i < W; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            waterPosList.add(new int[]{x, y});
        }

        // 땅
        groundArr = new Ground[N][N];
        for (int i = 0; i < N; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++)
                groundArr[i][j] = new Ground(i, j, str.charAt(j) - '0');
        }

        // 물 흐르는 시간 계산
        setWaterTime();

        System.out.println(dijkstra());
    }

    private static void setWaterTime()
    {
        Deque<int[]> bfsQ = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int[] pos : waterPosList)
        {
            int x = pos[0], y = pos[1];
            groundArr[x][y].waterTime = 0;
            bfsQ.offer(new int[]{x, y, 0});
            visited[x][y] = true;
        }

        while (!bfsQ.isEmpty())
        {
            int[] cur = bfsQ.poll();
            int x = cur[0], y = cur[1], t = cur[2];
            for (int i = 0; i < wdx.length; i++)
            {
                int nx = x + wdx[i];
                int ny = y + wdy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny])
                {
                    visited[nx][ny] = true;
                    groundArr[nx][ny].waterTime = t + 1;
                    bfsQ.offer(new int[]{nx, ny, t + 1});
                }
            }
        }
    }

    private static int dijkstra()
    {
        int[][] costArr = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(costArr[i], Integer.MAX_VALUE);

        PriorityQueue<int[]> dstQ = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[2], e2[2]));
        dstQ.offer(new int[]{0, 0, 0});
        costArr[0][0] = 0;

        while (!dstQ.isEmpty())
        {
            int[] cur = dstQ.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];

            if (c > costArr[x][y])
                continue;

            if (x == N - 1 && y == N - 1)
                return c;

            for (int i = 0; i < mdx.length; i++)
            {
                int nx = x + mdx[i];
                int ny = y + mdy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N)
                {
                    int nextTime = 0;

                    // 시작점이나 도착지는 무조건 갈 수 있음
                    if ((nx == 0 && ny == 0) || (nx == N - 1 && ny == N - 1))
                        nextTime = c;
                    else if (groundArr[nx][ny].v == 1)
                    {
                        if (groundArr[nx][ny].waterTime == Integer.MAX_VALUE)
                            continue; // 물이 안닿음

                        nextTime = Math.max(c, groundArr[nx][ny].waterTime);
                    }
                    else
                        continue;

                    if (nextTime < costArr[nx][ny])
                    {
                        costArr[nx][ny] = nextTime;
                        dstQ.offer(new int[]{nx, ny, nextTime});
                    }
                }
            }
        }
        return -1;
    }
}
