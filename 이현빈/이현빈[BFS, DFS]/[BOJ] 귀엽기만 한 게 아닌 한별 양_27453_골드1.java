import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static char[][] mapArr;
    static boolean[][][] visited; // visited[x][y][dir]

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mapArr = new char[N][M];
        visited = new boolean[N][M][4]; // 4방향

        for (int i = 0; i < N; i++)
        {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++)
                mapArr[i][j] = str.charAt(j);
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs()
    {
        Queue<int[]> queue = new ArrayDeque<>();

        // 학교 위치 찾기
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if(mapArr[i][j] == 'S')
                {
                    queue.offer(new int[]{i, j, 0, -1});
                    break;
                }
            }
        }

        while (!queue.isEmpty())
        {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            int currentDir = cur[3]; // 현재 칸에 어느 방향에서 도착했는지

            if(mapArr[x][y] == 'H')
                return dist;
                
            for (int d = 0; d < 4; d++)
            {
                // 바로 직전 칸으로 돌아가는 것 방지
                if (currentDir == d) continue;

                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || mapArr[nx][ny] == 'X')
                    continue;

                // 최근 3칸의 불상사 합 계산
                int sum = getMishap(nx, ny); // 새로 갈 칸
                sum += getMishap(x, y); // 현재 칸

                // 이전 칸 (currentDir 방향에서 왔으므로, 그 반대편이 이전 칸)
                if (currentDir >= 0) 
                {
                    int prevX = x + dx[currentDir];
                    int prevY = y + dy[currentDir];
                    sum += getMishap(prevX, prevY);
                }

                // K 이하이고 방문하지 않은 경우
                // 새 칸에 d 방향으로 도착하므로, 역방향을 저장
                int reverseDir = getReverseDir(d);

                if (sum <= K && !visited[nx][ny][reverseDir])
                {
                    visited[nx][ny][reverseDir] = true;
                    queue.offer(new int[]{nx, ny, dist + 1, reverseDir});
                }
            }
        }

        return -1;
    }

    private static int getMishap(int x, int y)
    {
        char cell = mapArr[x][y];
        if (cell == 'S' || cell == 'H') return 0;
        if (cell >= '0' && cell <= '9') return cell - '0';
        return 0;
    }

    private static int getReverseDir(int dir)
    {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        if (dir == 3) return 2;
        return -1;
    }
}
