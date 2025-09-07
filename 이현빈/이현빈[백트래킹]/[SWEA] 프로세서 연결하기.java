import java.io.*;
import java.util.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
 
    static int T, N, MIN, startCoreCount, coreCount, tempMIN, maxTemp;
    static int[][] mapArr; // -1 : 코어인데 전선 연결 안해도 됨
    static boolean[][] checkArr;
    static List<int[]> coreList;
 
    public static void main(String[] args) throws IOException
    {
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++)
        {
            N = Integer.parseInt(br.readLine());
            mapArr = new int[N][N];
            checkArr = new boolean[N][N];
            coreList = new ArrayList<>();
            coreCount = 0;
            startCoreCount = 1;
            maxTemp = 0;
            MIN = Integer.MAX_VALUE;
 
            for (int i = 0; i < N; i++)
            {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                {
                    mapArr[i][j] = Integer.parseInt(st.nextToken());
 
                    if(mapArr[i][j] == 1)
                    {
                        if(i == 0 && j >= 0 && j < N) mapArr[i][j] = -1; // 좌
                        else if(i == N - 1 && j >= 0 && j < N) mapArr[i][j] = -1; // 우
                        else if(j == 0 && i >= 0 && i < N) mapArr[i][j] = -1; // 상
                        else if(j == N - 1 && i >= 0 && i < N) mapArr[i][j] = -1; // 하
 
                        if(mapArr[i][j] == 1)
                        {
                            coreList.add(new int[] {i, j});
                            coreCount++;
                        }
                    }
                }
            }
 
            int edgeCoreCount = 0;
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if(mapArr[i][j] == -1)
                        edgeCoreCount++;
                }
            }
            maxTemp = edgeCoreCount;
 
            // 일단 그냥 0번째부터 시작 - 모든 코어 조합 탐색
            dfs(0, 0, edgeCoreCount);
 
            sb.append("#").append(t).append(" ").append(MIN).append("\n");
        }
 
        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);
 
        System.out.println(sb);
    }
 
    private static void dfs(int idx, int wireCount, int connectedCoreCount)
    {
        // b - 모든 코어를 다 확인했을 때
        if(idx == coreCount)
        {
            // boolean의 true 갯수만큼 count 해보고 min 갱신 ->  return
 
            int cnt = wireCount; // 전선 개수
 
            // 얘가 스타트코어카운트에서 구할 수 있느 가장 작은 값 !
            //System.out.println(idx + " " + cnt + " " + maxTemp + " " + tempMIN + " " + MIN);
 
            // 최대 코어 수 갱신 또는 같은 코어 수일 때 최소 전선 길이 갱신
            if(connectedCoreCount > maxTemp)
            {
                maxTemp = connectedCoreCount;
                MIN = cnt;
            }
            else if(connectedCoreCount == maxTemp && cnt < MIN)
            {
                MIN = cnt;
            }
 
            return;
        }
 
        // i
 
        // 넘겨 받은 애 4방 탐색 해볼겁니다.
 
        // 갈 수있는 곳이라면 거기 끝까지 다 true로 줘버리고 dfs 호출
 
        int[] cur = coreList.get(idx);
 
        for (int i = 0; i < dx.length + 1; i++)
        {
            if(i == 4)
            {
                dfs(idx + 1, wireCount, connectedCoreCount);
            }
            else
            {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
 
                // 첫 칸부터 체크해야 함
                // i 방향으로 갈 수 있는 만큼 가봐. 가다가 true를 마주치면 안되고, 코어도 마주치면 안됨 !! 끝이 항상 사이드여야함 !!
                // 도달 했다면 그 동안의 정보를 다 큐에 담아
 
                boolean isEnd = false;
                List<int[]> trueQ = new ArrayList<>();
 
                while(true)
                {
                    // 경계 체크 먼저
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        break;
 
                    // 장애물 체크
                    if(checkArr[nx][ny] || mapArr[nx][ny] != 0)
                        break;
 
                    if(nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1)
                    {
                        isEnd= true;
                        trueQ.add(new int[] {nx,ny});
                        break;
                    }
 
                    trueQ.add(new int[] {nx,ny});
 
                    nx = nx + dx[i];
                    ny = ny + dy[i];
                }
 
                if(isEnd)
                {
                    for (int j = 0; j < trueQ.size(); j++)
                        checkArr[trueQ.get(j)[0]][trueQ.get(j)[1]] = true;
 
                    dfs(idx + 1, wireCount + trueQ.size(), connectedCoreCount + 1);
 
                    for (int j = 0; j < trueQ.size(); j++)
                        checkArr[trueQ.get(j)[0]][trueQ.get(j)[1]] = false;
                }
            }
        }
    }
 
    static void PrintArr()
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(mapArr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
 
    static void PrintBooleanArr()
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(checkArr[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
