import java.io.*;
import java.util.*;
public class Main {
    static int row;
    static int col;
    static int k;
    static int[][][] map;
    static boolean[] v;
    static int[][] rotateCase;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[k+1][row][col];
        v = new boolean[k];
        rotateCase = new int[k][3];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                map[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotateCase[i][0] = Integer.parseInt(st.nextToken())-1;
            rotateCase[i][1] = Integer.parseInt(st.nextToken())-1;
            rotateCase[i][2] = Integer.parseInt(st.nextToken());
        }
        min = Integer.MAX_VALUE;
        permutation(1);
        System.out.println(min);
    }
    public static void permutation(int count){
        if(count == k+1) {
            calculate();
            return;
        }

        for(int i = 0; i < k; i++){
            if(!v[i]){
                v[i] =true;
                rotate(count, i);
                permutation(count + 1);
                v[i] = false;
            }
        }
    }
    private static void calculate(){
        for(int i = 0; i < row; i++){
            int sum = 0;
            for(int j = 0; j< col; j++){
                sum += map[k][i][j];
            }
            min = Math.min(min, sum);
        }

    }
    private static void rotate(int count, int rNum){

        int r = rotateCase[rNum][0];
        int c = rotateCase[rNum][1];
        int size = rotateCase[rNum][2];

        int startR = r-size;
        int startC = c-size;
        int endR = r+size;
        int endC = c+size;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                map[count][i][j] = map[count-1][i][j];
            }
        }
        while(startR < endR && startC < endC){
            for(int i = startR; i <= endR; i++){
                if(i+1 <= endR) map[count][i][startC] = map[count-1][i+1][startC];
                if(i-1 >= startR) map[count][i][endC] = map[count-1][i-1][endC];
            }
            for(int i = startC; i <= endC; i++){
                if(i-1 >= startC) map[count][startR][i] = map[count-1][startR][i-1];
                if(i+1 <= endC) map[count][endR][i] = map[count-1][endR][i+1];
            }
            startR++;
            startC++;
            endR--;
            endC--;
        }
    }
}

