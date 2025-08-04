import java.io.*;
import java.util.*;
public class Main {
    static int[] dCol = {1, 0, -1, 0};
    static int[] dRow = {0, 1, 0, -1};
    static int col;
    static int row;
    static int time;
    static char[][] map;
    static boolean[][][] bTime;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());
        map = new char[col][row];
        bTime = new boolean[time + 4][col][row];
        for (int i = 0; i < col; i++) {
            String s = br.readLine();
            for (int j = 0; j < row; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'O') bTime[3][i][j] = true;
            }
        }

        for(int i = 1; i <= time; i++){
            check(i);
            if(i % 2 == 0){
                fillRest(i);
            }
        }

        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void fillRest(int time){
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                if(map[i][j] == '.'){
                    map[i][j] = 'O';
                    bTime[time+3][i][j] = true;
                }
            }
        }
    }
    public static void check(int now){
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                if(bTime[now][i][j]){
                    map[i][j] = '.';
                    for(int k = 0; k < 4; k++){
                        int nCol = i + dCol[k];
                        int nRow = j + dRow[k];
                        if(outOfRange(nCol, nRow)) continue;
                        map[nCol][nRow] = '.';
                        for(int t = 1; t < 3; t++){
                            bTime[now + t][nCol][nRow] = false;
                        }
                    }
                }
            }
        }
    }
    public static boolean outOfRange(int nC, int nR){
        if(nC >= col || nC < 0 || nR >=row || nR < 0) return true;
        return false;
    }
}
