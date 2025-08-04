import java.io.*;
import java.util.*;
public class Main {
    public static int count = 0;
    public static int n;
    public static int[][] map;
    public static int[][] vector = {{1, 0}, {0, 1}, {1, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j< n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0, 0, 1, 0);
        System.out.print(count);

    }
    public static void dfs(int sC, int sR, int eC, int eR, int type){
        if(eC == n-1 && eR == n-1){
            count++;
            return;
        }
        int nC_1 = eC + vector[1][0]; //가로
        int nR_1 = eR + vector[1][1]; //가로

        int nC_2 = eC + vector[0][0]; //세로
        int nR_2 = eR + vector[0][1]; //세로

        int nC_3 = eC + vector[2][0]; //대각선
        int nR_3 = eR + vector[2][1]; //대각선
        switch(type){
            case 0 : {
                if(check(nC_1, nR_1) && map[nC_1][nR_1] != 1){
                    dfs(eC, eR, nC_1, nR_1, 0);
                }
                if(check(nC_3, nR_3) && map[nC_1][nR_1] != 1 && map[nC_2][nR_2] != 1 && map[nC_3][nR_3] != 1){
                    dfs(eC, eR, nC_3, nR_3, 2);
                }
                break;
            }
            case 1 : {
                if(check(nC_2, nR_2) && map[nC_2][nR_2] != 1){
                    dfs(eC, eR, nC_2, nR_2, 1);
                }
                if(check(nC_3, nR_3) && map[nC_1][nR_1] != 1 && map[nC_2][nR_2] != 1 && map[nC_3][nR_3] != 1){
                    dfs(eC, eR, nC_3, nR_3, 2);
                }
                break;
            }
            case 2 : {
                if(check(nC_1, nR_1) && map[nC_1][nR_1] != 1){
                    dfs(eC, eR, nC_1, nR_1, 0);
                }
                if(check(nC_2, nR_2) && map[nC_2][nR_2] != 1){
                    dfs(eC, eR, nC_2, nR_2, 1);
                }
                if(check(nC_3, nR_3) && map[nC_1][nR_1] != 1 && map[nC_2][nR_2] != 1 && map[nC_3][nR_3] != 1){
                    dfs(eC, eR, nC_3, nR_3, 2);
                }
                break;
            }
        }
    }
    public static boolean check(int col, int row){
        if(col == n || col < 0 || row == n || row < 0) return false;
        return true;
    }

}
