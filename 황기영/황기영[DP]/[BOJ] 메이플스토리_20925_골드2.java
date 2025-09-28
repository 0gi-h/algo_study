import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20925 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] dungeon = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dungeon[i][0] = Integer.parseInt(st.nextToken());
            dungeon[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] move = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                move[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[][] dp = new int[n][t + 1];

        for (int i = 1; i <= t; i++) {
            for (int j = 0; j < n; j++) {


                if (dp[j][i - 1] >= dungeon[j][0]) {
                    dp[j][i] = dp[j][i - 1] + dungeon[j][1];
                }


                for (int k = 0; k < n; k++) {
                    int prevTime = i - move[k][j];
                    if (k == j || prevTime < 0) continue;

                    if (dp[k][prevTime] >= dungeon[j][0]) {
                        dp[j][i] = Math.max(dp[j][i], dp[k][prevTime]);
                    }
                }
            }
        }

        int max = 0;
        for (int room = 0; room < n; room++) {
            for (int time = 0; time <= t; time++) {
                max = Math.max(max, dp[room][time]);
            }
        }

        System.out.println(max);
    }
}
