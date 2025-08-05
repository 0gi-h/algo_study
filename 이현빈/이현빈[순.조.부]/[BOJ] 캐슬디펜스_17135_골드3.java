import java.io.*;
import java.util.*;

public class CastleDefence_250804
{
    static List<int[]> archerList = new ArrayList<>();
    static int max = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        int castleArr[][] = new int[row + 1][col];

        for (int i = 0; i < row; i++)
        {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < col; j++)
                castleArr[i][j] = Integer.parseInt(st.nextToken());
        }

        // 궁수 배치도 뽑기 위한 자료
        int arrows[] = new int[col];

        for (int i = 0; i < col; i++)
            arrows[i] = i;

        int sel[] = new int[3];

        recursive(arrows, sel, 0, 0);

        for (int i = 0; i < archerList.size(); i++)
            max = Math.max(max, simulator(archerList.get(i), castleArr, row, col, distance));

        System.out.println(max);
    }

    private static int simulator(int[] archerPosArr, int[][] castleArr, int row, int col, int distance)
    {
        int shotCount = 0;

        int castleTemp[][] = new int[row + 1][col];
        for (int i = 0; i < row; i++)
            castleTemp[i] = Arrays.copyOf(castleArr[i], col);

        for (int l = 0; l < row; l++)
        {
            // 중복으로 쏘려고
            Set<int[]> attacked = new HashSet<>();

            // 궁수 3명에 대해 각자 타겟 탐색
            for (int i = 0; i < archerPosArr.length; i++)
            {
                int startX = row;
                int startY = archerPosArr[i];

                boolean[][] visited = new boolean[row + 1][col];
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{startX, startY, 0});
                visited[startX][startY] = true;

                int targetX = -1;
                int targetY = -1;

                // 왼 → 위 → 오 우선순위
                int[] dx = {0, -1, 0};
                int[] dy = {-1, 0, 1};

                while (!q.isEmpty())
                {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    int d = cur[2];

                    if (d > distance) break; // 적이 있긴한데 내 힘보다 멀리 있음

                    // 쏠쑤있써ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ
                    if (x < row && castleTemp[x][y] == 1)
                    {
                        targetX = x;
                        targetY = y;
                        break;
                    }

                    // 갈 수 있는 칸 탐색
                    for (int j = 0; j < 3; j++)
                    {
                        int nx = x + dx[j];
                        int ny = y + dy[j];

                        if (nx >= 0 && ny >= 0 && nx <= row && ny < col && !visited[nx][ny])
                        {
                            visited[nx][ny] = true;
                            q.offer(new int[]{nx, ny, d + 1});
                        }
                    }
                }

                // y는 차피 그대로에요
                if (targetX != -1)
                    attacked.add(new int[]{targetX, targetY});
            }

            // 공격 수행
            for (int[] pos : attacked)
            {
                int x = pos[0];
                int y = pos[1];

                if (castleTemp[x][y] == 1)
                {
                    castleTemp[x][y] = 0;
                    shotCount++;
                }
            }

            // 내려왓
            for (int x = row - 1; x > 0; x--)
                castleTemp[x] = Arrays.copyOf(castleTemp[x - 1], col);

            Arrays.fill(castleTemp[0], 0);
        }

        return shotCount;
    }

    private static void recursive(int[] arrows, int[] sel, int idx, int k)
    {
        // b
        if (k == 3)
        {
            int temp[] = new int[3];

            for (int i = 0; i < 3; i++)
                temp[i] = sel[i];

            archerList.add(temp);

            return;
        }

        if (idx == arrows.length) return;

        // i
        sel[k] = arrows[idx];
        recursive(arrows, sel, idx + 1, k + 1);

        recursive(arrows, sel, idx + 1, k);
    }
}
