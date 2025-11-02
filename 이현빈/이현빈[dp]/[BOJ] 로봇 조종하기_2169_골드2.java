import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][M];
        int[][] left = new int[N][M];
        int[][] right = new int[N][M];

        dp[0][0] = map[0][0];
        for (int j = 1; j < M; j++)
            dp[0][j] = dp[0][j - 1] + map[0][j];

        for (int i = 1; i < N; i++)
        {
            left[i][0] = dp[i - 1][0] + map[i][0];
            for (int j = 1; j < M; j++)
                left[i][j] = Math.max(dp[i - 1][j], left[i][j - 1]) + map[i][j];
                
            right[i][M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
            for (int j = M - 2; j >= 0; j--)
                right[i][j] = Math.max(dp[i - 1][j], right[i][j + 1]) + map[i][j];

            for (int j = 0; j < M; j++)
                dp[i][j] = Math.max(left[i][j], right[i][j]);
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}
