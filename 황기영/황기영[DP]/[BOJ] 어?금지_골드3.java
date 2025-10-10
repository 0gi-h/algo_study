import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[3][n];
        long[] dp = new long[n];

        // arr[0] = t
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[0][i] = Integer.parseInt(st.nextToken());
        }

        // arr[1] = b
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[1][i] = Integer.parseInt(st.nextToken());
        }

        // arr[2] = c
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[2][i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[2][0];  

        for (int i = 1; i < n; i++) {
            int t = arr[0][i];
            int b = arr[1][i];
            long c = arr[2][i];

            int target = t - b;

            int prevIdx = lowerBound(arr[0], 0, i + 1, target) - 1;

            dp[i] = Math.max(dp[i - 1], c);
            if (prevIdx >= 0) {
                dp[i] = Math.max(dp[i], dp[prevIdx] + c);
            }
        }

        System.out.println(dp[n - 1]);
    }

    private static int lowerBound(int[] arr, int left, int right, int target) {
        int lo = left;
        int hi = right;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
