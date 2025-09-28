import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[m+1][n];
        int[][] exp = new int[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            exp[i][0] = Integer.parseInt(st.nextToken());
            exp[i][1] = Integer.parseInt(st.nextToken());
        }
        int[][] edges = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                edges[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= m; i++){
            for(int j = 0; j < n; j++){
                if(dp[i-1][j] >= exp[j][0]) dp[i][j] = dp[i-1][j]+exp[j][1];
                for(int k = 0; k < n; k++){
                    if(i-edges[k][j] < 0 || dp[i-edges[k][j]][k] < exp[j][0]) continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i-edges[k][j]][k]);
                }
            }
        }
        int result = 0;
        for(int i = 0; i < n; i++){
            result = Math.max(result, dp[m][i]);
        }
        System.out.println(result);
    }
}
