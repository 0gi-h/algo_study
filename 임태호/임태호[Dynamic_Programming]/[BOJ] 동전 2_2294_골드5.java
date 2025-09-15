import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k =  Integer.parseInt(st.nextToken());
        int[] dp = new int[k+1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;
        int[] coins = new int[n];
        for(int i = 0; i < n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        // 백트래킹을 위해 정렬을 했습니다.
        Arrays.sort(coins);
        for(int i = 1; i <= k; i++){
            for(int j = 0; j < n; j++){
                if(i - coins[j] < 0) break;
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        System.out.println(dp[k] != 10001 ? dp[k] : -1);
    }
}
