import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] pointX = new int[N];
        int[] pointY = new int[N];

        for(int i = 0; i < N; i++){
            st= new StringTokenizer(br.readLine());
            pointX[i]= Integer.parseInt(st.nextToken());
            pointY[i] =Integer.parseInt(st.nextToken());
        }
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                dist[i][j] = Math.abs(pointX[j]-pointX[i]) + Math.abs(pointY[j] - pointY[i]);
            }   
        }

        int[][] dp = new int[K+1][N];
        for(int i = 0; i <= K; i++){
            Arrays.fill(dp[i], 123456789);
        }        
        dp[0][0] = 0;

        
        for(int i = 0; i <= K; i++){
            for(int j = 1; j < N; j++){
                dp[i][j] = dp[i][j-1] + dist[j-1][j];
                for(int k = 0; k < j; k++){
                    if(i-(j-k-1) >= 0) dp[i][j] = Math.min(dp[i][j] , dp[i-(j-k-1)][k] + dist[k][j]);
                }
            }
        }
        
        int min = 123456789;
        for(int i = 0; i <= K; i++){
            min = Math.min(dp[i][N-1] , min);
        }
        System.out.println(min);

    }
}
