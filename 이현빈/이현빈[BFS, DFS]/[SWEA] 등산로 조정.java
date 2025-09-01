import java.io.*;
import java.util.*;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int N, K;
    static int[][] map;
    static boolean[][] v;
    static int ans;
    static int maxH;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
 
    public static void main(String[] args) throws Exception
    {
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int t = 1; t <= T; t++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            map = new int[N][N];
            maxH = 0;
            for (int i = 0; i < N; i++)
            {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxH = Math.max(maxH, map[i][j]);
                }
            }
 
            ans = 0;
            v = new boolean[N][N];
 
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    if (map[i][j] == maxH) // 제일 높은곳
                    {
                        v[i][j] = true;
                        dfs(i, j, 1, false);
                        v[i][j] = false;
                    }
                }
            }
 
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);
         
        System.out.print(sb);
    }
 
    static void dfs(int x, int y, int len, boolean usedCut)
    {
        ans = Math.max(ans, len);
 
        for (int i = 0; i < dx.length; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && !v[nx][ny])
            {
                if (map[nx][ny] < map[x][y]) // 일단 가
                {
                    v[nx][ny] = true;
                    dfs(nx, ny, len + 1, usedCut);
                    v[nx][ny] = false;
                }
                else if (!usedCut)
                {
                    int saved = map[nx][ny];
 
                    for (int j = 1; j <= K; j++)
                    {
                        int lowered = saved - j;
 
                        if (lowered < map[x][y])
                        {
                            map[nx][ny] = lowered; // 깍음
                            v[nx][ny] = true;
                            dfs(nx, ny, len + 1, true);
                            v[nx][ny] = false;
                            map[nx][ny] = saved; // 돌려놓음
                        }
                    }
                }
            }
        }
    }
}
