import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int life1 = 0;
        int life2 = 0;
        int life3 = 0;
        switch(n) {
            case 1 :
                life1 = Integer.parseInt(br.readLine());
                System.out.println(life1 % 9 == 0 ? life1 / 9 : life1 / 9 + 1);
                break;
            case 2 :
                st = new StringTokenizer(br.readLine());
                life1 = Integer.parseInt(st.nextToken());
                life2 = Integer.parseInt(st.nextToken());
                int[][] dp = new int[life1+1][life2+1];
                for(int i = 0; i <= life1; i++){
                    int preI1 = Math.max(i - 9 , 0);
                    int preI2 = Math.max(i - 3, 0);
                    for(int j = 0; j <= life2; j++){
                        if(i == 0 && j == 0) continue;
                        int preJ1 = Math.max(j - 3, 0);
                        int preJ2 = Math.max(j - 9, 0);
                        dp[i][j] = Math.min(dp[preI1][preJ1], dp[preI2][preJ2]) + 1;
                    }
                }
                System.out.println(dp[life1][life2]);
                break;
            case 3 :
                st = new StringTokenizer(br.readLine());
                life1 = Integer.parseInt(st.nextToken());
                life2 = Integer.parseInt(st.nextToken());
                life3 = Integer.parseInt(st.nextToken());
                int[][][] dp2 = new int[life1+1][life2+1][life3+1];
                for(int i = 0; i <= life1; i++){
                    int preI1 = Math.max(i - 9 , 0);
                    int preI2 = Math.max(i - 3, 0);
                    int preI3 = Math.max(i - 1, 0);
                    for(int j = 0; j <= life2; j++){
                        int preJ1 = Math.max(j - 9 , 0);
                        int preJ2 = Math.max(j - 3, 0);
                        int preJ3 = Math.max(j - 1, 0);
                        for(int k = 0; k <= life3; k++){
                            if(i == 0 && j == 0 && k == 0) continue;
                            int preK1 = Math.max(k - 9 , 0);
                            int preK2 = Math.max(k - 3, 0);
                            int preK3 = Math.max(k - 1, 0);
                            dp2[i][j][k] = Math.min(dp2[preI1][preJ2][preK3] , dp2[preI1][preJ3][preK2]) + 1;
                            dp2[i][j][k] = Math.min(dp2[i][j][k] , dp2[preI2][preJ1][preK3] + 1);
                            dp2[i][j][k] = Math.min(dp2[i][j][k] , dp2[preI2][preJ3][preK1] + 1);
                            dp2[i][j][k] = Math.min(dp2[i][j][k] , dp2[preI3][preJ1][preK2] + 1);
                            dp2[i][j][k] = Math.min(dp2[i][j][k] , dp2[preI3][preJ2][preK1] + 1);
                        }
                    }
                }
                System.out.println(dp2[life1][life2][life3]);



                break;
            default :
                break;
        }
    }

}
