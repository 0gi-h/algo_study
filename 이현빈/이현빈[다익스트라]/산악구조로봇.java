import java.io.*;
import java.util.*;

public class test
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N;
    static int[][] mapArr;
    static int[][] costArr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException
    {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++)
        {
            N = Integer.parseInt(br.readLine());
            mapArr = new int[N][N];
          
            for (int i = 0; i < N; i++)
            {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    mapArr[i][j] = Integer.parseInt(st.nextToken());
            }
          
            costArr = new int[N][N];
            for (int i = 0; i < N; i++)
                Arrays.fill(costArr[i], Integer.MAX_VALUE);
            
            sb.append("#").append(t).append(" ").append(dijkstra()).append("\n");
        }
      
        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }

    private static int dijkstra()
    {
        PriorityQueue<int[]> dstQ = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2)
            {
                return Integer.compare(o1[2], o2[2]); // 2번째에 비용 담을꺼임
            }
        });
        dstQ.offer(new int[]{0, 0, 0});
        costArr[0][0] = 0; // 초기화

        while (!dstQ.isEmpty())
        {
            int[] cur = dstQ.poll();

            if(cur[0] == N - 1 && cur[1] == N -1) // 목적지
                return cur[2];

            if(cur[2] > costArr[cur[0]][cur[1]]) // 이미 최소
                continue;

            for (int i = 0; i < dx.length; i++)
            {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N)
                {
                    int nc = 0;

                    // 평지
                    if(mapArr[cur[0]][cur[1]] == mapArr[nx][ny])
                        nc = 1;

                    // 내리막
                    else if(mapArr[cur[0]][cur[1]] > mapArr[nx][ny])
                        nc = 0;

                    // 오르막
                    else
                        nc = (mapArr[nx][ny] - mapArr[cur[0]][cur[1]]) * 2;

                    if(nc + cur[2] < costArr[nx][ny]) // 최소비용 갱신
                    {
                        costArr[nx][ny] = nc + cur[2];
                        dstQ.offer(new int[]{nx, ny, nc + cur[2]});
                    }
                }
            }
        }

        return -1; // 도착하지 못할 일은 없긴한데 걍 -1
    }
}
