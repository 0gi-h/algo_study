import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        dp[0] = 1;
        dp[1] = 3;

        for (int i = 2; i <= N; i++)
            dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;

        System.out.println(dp[N]);
    }
}
