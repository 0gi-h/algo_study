import java.io.*;
import java.util.*;

public class Main {
    static class Land
    {
        int x, y, number;

        Land(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, islandCnt;
    static int[][] mapArr;
    static Land[][] landArr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int INF = 987654321;

    public static void main(String[] args) throws Exception
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapArr = new int[N][M];
        landArr = new Land[N][M];

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
            {
                mapArr[i][j] = Integer.parseInt(st.nextToken());
                if (mapArr[i][j] == 1) landArr[i][j] = new Land(i, j);
            }
        }

        int landNumber = 1;

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (landArr[i][j] != null && landArr[i][j].number == 0)
                {
                    SetNumber(i, j, landNumber); // 섬 번호 부여해주기
                    landNumber++;
                }
            }
        }

        islandCnt = landNumber - 1;

        if (islandCnt <= 1) // 섬이 1개이하
        {
            System.out.println(0);
            return;
        }

        int[][] dist = new int[landNumber][landNumber];

        for (int i = 0; i < landNumber; i++)
            Arrays.fill(dist[i], INF);

        for (int x = 0; x < N; x++)
        {
            for (int y = 0; y < M; y++)
            {
                if (landArr[x][y] == null) // 여긴 섬이 아니여
                    continue;

                int from = landArr[x][y].number; // 시작 지점

                for (int i = 0; i < dx.length; i++)
                {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (!checkArea(nx, ny) || mapArr[nx][ny] != 0)
                        continue;

                    int len = 0;

                    while (checkArea(nx, ny) && mapArr[nx][ny] == 0) // 바다를 만나지 않거나.. 혹은 맵을 나갈때까지 그 방향으로 직진
                    {
                        nx += dx[i];
                        ny += dy[i];
                        len++;
                    }

                    if (checkArea(nx, ny) && landArr[nx][ny] != null) // 끝났는데 여기가 섬인가?
                    {
                        int to = landArr[nx][ny].number; // 도착 지점

                        if (to != from && len >= 2) // 시작 지점과 도착 지점이 같지 않고 섬의 길이가 2 이상일때만
                        {
                            if (len < dist[from][to]) // 최솟값만 !
                            {
                                dist[from][to] = len; // 여기까지 올 수 있는 거리를 저장
                                dist[to][from] = len;
                            }
                        }
                    }
                }
            }
        }

        // 초기화
        boolean[] visited = new boolean[islandCnt + 1]; // 정점 포함 여부
        int[] key = new int[islandCnt + 1]; // 최소비용
        Arrays.fill(key, INF);
        key[1] = 0; // 여기부터 시작

        int taken = 0; // 포함된 정점 수 갯수
        int answer = 0; // 다리길이 총 합 저장 될 변수

        for (int i = 0; i < islandCnt; i++) // 모든 섬 다 돌아볼거에여
        {
            int u = -1;
            int best = INF;

            for (int j = 1; j <= islandCnt; j++)
            {
                if (!visited[j] && key[j] < best) // 방문하지 않은 섬인데 내가 더 짧게 갈 수 있는데?
                {
                    // 갱신
                    best = key[j];
                    u = j; // 이제부터 제일 짧은놈은 나다
                }
            }
            if (u == -1) // 제일 짧은 놈을 갱신하지 못함
            {
                System.out.println(-1);
                return;
            }

            visited[u] = true;
            taken++;

            answer += (best == INF ? 0 : best); // 총 다리길이 합에 제일 짧은 다리 갱신

            for (int v = 1; v <= islandCnt; v++)
            {
                if (!visited[v]) // 아직 포함되지 않은 정점이고
                {
                    if (dist[u][v] < key[v])  // u를 통해 가는 다리 길이가 현재 알려진 최소 비용보다 짧으면 갱신
                        key[v] = dist[u][v];
                }
            }
        }

        if (taken != islandCnt) // 모든 섬을 다 돌아보지 못함 == 연결이 안되어있음
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    // 이 영역이 맵 안에 있는지 체크 해주는 함수
    static boolean checkArea(int x, int y)
    {
        return 0 <= x && 0 <= y && x < N && y < M;
    }

    // 섬 번호 부여해주기
    private static void SetNumber(int x, int y, int landNumber)
    {
        landArr[x][y].number = landNumber;
        Deque<int[]> bfsQ = new ArrayDeque<>();
        bfsQ.offer(new int[]{x, y});
        while (!bfsQ.isEmpty())
        {
            int[] p = bfsQ.poll();

            for (int d = 0; d < 4; d++)
            {
                int nx = p[0] + dx[d], ny = p[1] + dy[d];
                if (checkArea(nx, ny) && mapArr[nx][ny] == 1 && landArr[nx][ny].number == 0)
                {
                    landArr[nx][ny].number = landNumber;
                    bfsQ.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
