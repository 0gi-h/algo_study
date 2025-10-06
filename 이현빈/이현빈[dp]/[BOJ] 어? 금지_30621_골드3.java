import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static long[] timeArr, compareArr, costArr;
    static long[] dp;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());

        // t
        timeArr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            timeArr[i] = Long.parseLong(st.nextToken());

        // b
        compareArr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            compareArr[i] = Long.parseLong(st.nextToken());

        // c
        costArr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            costArr[i] = Long.parseLong(st.nextToken());

        dp = new long[N + 1];

        for (int i = 1; i <= N; i++)
        {
            dp[i] = dp[i - 1];

            long threshold = timeArr[i - 1] - compareArr[i - 1];

            int idx = binarySearch(threshold);

            dp[i] = Math.max(dp[i], dp[idx] + costArr[i - 1]);
        }

        System.out.println(dp[N]);
    }

    static int binarySearch(long threshold)
    {
        int left = 0;
        int right = N - 1;
        int result = 0;

        while (left <= right)
        {
            int mid = (left + right) / 2;

            if (timeArr[mid] < threshold)
            {
                result = mid + 1;
                left = mid + 1;
            }
            else
                right = mid - 1;
        }

        return result;
    }
}
