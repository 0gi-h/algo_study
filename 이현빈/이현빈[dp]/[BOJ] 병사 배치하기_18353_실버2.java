import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] numArr, dp;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (numArr[i] < numArr[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int maxLen = 0;
      
        for (int len : dp)
            maxLen = Math.max(maxLen, len);

        System.out.println(N - maxLen);
    }
}
