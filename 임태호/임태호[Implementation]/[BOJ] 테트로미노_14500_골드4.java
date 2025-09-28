
import java.io.*;
import java.util.*;
public class Main {
    static int[][] map ;
    static int n;
    static int m;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map= new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = 0;
        bf();
        System.out.println(max);
    }
    public static void bf(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                type1(i, j);
                type2(i, j);
                type3(i, j);
                type4(i, j);
                type5(i, j);
            }
        }
    }
    public static void type1(int row, int col){
        if(col + 4 <= m) {
            int count = 0;
            for(int i = col; i < col + 4; i++){
                count+= map[row][i];
            }
            max = Math.max(max, count);
        }

        if(row + 4 <= n){
            int count = 0;
            for(int i = row; i < row + 4; i++){
                count+= map[i][col];
            }
            max = Math.max(max, count);
        }
    }
    public static void type2(int row, int col){
        if(row + 2 <= n && col + 2 <= m){
            int count = 0;
            for(int i = row; i < row + 2; i++){
                for(int j = col; j < col + 2; j++){
                    count+= map[i][j];
                }
            }
            max = Math.max(max, count);
        }
    }
    public static void type3(int row, int col){
        if(row + 3 <= n){
            int count = 0;
            for(int i = row; i < row + 3; i++){
                count += map[i][col];
            }
            if(col - 1 >= 0){
                max = Math.max(Math.max(max, count + map[row+2][col-1]) , count + map[row][col-1]);
            }
            if(col + 1 < m){
                max = Math.max(Math.max(max, count + map[row+2][col+1]) , count + map[row][col+1]);
            }
        }

        if(col + 3 <= m){
            int count = 0;
            for(int i = col; i < col + 3; i++){
                count += map[row][i];
            }
            if(row + 1 < n){
                max = Math.max(Math.max(max, count + map[row+1][col]) , count + map[row+1][col+2]);
            }
            if(row -1 >=0){
                max = Math.max(Math.max(max, count + map[row-1][col]) , count + map[row-1][col+2]);
            }
        }
    }
    public static void type4(int row, int col){
        if(row + 3 <= n){
            int count = 0;
            for(int i = row; i < row + 2; i++){
                count+= map[i][col];
            }
            if(col + 1 < m){
                max = Math.max(max, count + map[row+1][col+1] + map[row+2][col+1]);
            }
            if(col - 1 >= 0){
                max = Math.max(max, count + map[row+1][col-1] + map[row+2][col-1]);
            }
        }
        if(col + 3 <= m){
            int count = 0;
            for(int i = col; i < col + 2; i++){
                count += map[row][i];
            }
            if(row + 1 < n){
                max = Math.max(max, count + map[row+1][col+1] + map[row+1][col+2]);
            }
            if(row -1 >=0){
                max = Math.max(max, count + map[row-1][col+1] + map[row-1][col+2]);
            }
        }
    }
    public static void type5(int row, int col){
        if(row + 3 <= n){
            int count = 0;
            for(int i = row; i < row + 3; i++){
                count += map[i][col];
            }
            if(col - 1 >= 0){
                max = Math.max(max, count + map[row+1][col-1]);
            }
            if(col + 1 < m){
                max = Math.max(max, count + map[row+1][col+1]);
            }
        }

        if(col + 3 <= m){
            int count = 0;
            for(int i = col; i < col + 3; i++){
                count += map[row][i];
            }
            if(row - 1 >= 0){
                max = Math.max(max, count + map[row-1][col+1]);
            }
            if(row + 1 < n){
                max = Math.max(max, count + map[row+1][col+1]);
            }
        }
    }
}
