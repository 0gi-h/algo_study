import java.io.*;
import java.util.*;
public class Main {
    static final int fixedValue = 1000 * 1000 * 100 * -1 - 1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 현재 가려는 방향
        int[][] dp = new int[N][M];
        int[] tmp = new int[M];
        int[] tmp2 = new int[M];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], fixedValue);
            Arrays.fill(tmp, fixedValue);
            Arrays.fill(tmp2, fixedValue);
        }
        dp[0][0] = map[0][0];
        for(int j = 1; j < M; j++){
            dp[0][j] = dp[0][j-1] + map[0][j];
        }



        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                tmp[j] = Math.max(dp[i-1][j], tmp[Math.max(j-1, 0)]) + map[i][j];
            }
            for(int j = M-1; j >= 0; j--){
                tmp2[j] = Math.max(dp[i-1][j], tmp2[Math.min(j+1, M-1)])  + map[i][j];
            }

            for(int j = 0; j < M; j++){
                dp[i][j] = Math.max(tmp[j], tmp2[j]);
            }
        }
        System.out.println(dp[N-1][M-1]);


    }
}
