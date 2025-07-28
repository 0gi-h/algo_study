import java.io.*;
import java.util.*;
public class Main{
    public static int[] dCol = {1, 0, -1, 0};
    public static int[] dRow = {0, 1, 0 ,-1};
    public static void main(String args[]) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int m =Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(br.readLine());
        if(target > n * m) sb.append(0);
        else{
            int dir = 0;
            int col = 0;
            int row = 0;
            int bCol = 0;
            int bRow = 0;
            int tCol = n-1;
            int tRow = m-1;
            for(int i = 1; i < target; i++){
                if(col + dCol[dir] > tCol || col + dCol[dir] < bCol || row + dRow[dir] > tRow || row + dRow[dir] < bRow){
                    switch(dir){
                        case 0 : {
                            bRow++;
                            break;
                        }
                        case 1 : {
                            tCol--;
                            break;
                        }
                        case 2 : {
                            tRow--;
                            break;
                        }
                        case 3 : {
                            bCol++;
                            break;
                        }
                    }
                    dir = (dir+1)%4;
                }
                col += dCol[dir];
                row += dRow[dir];
            }
            sb.append(row+1).append(" ").append(col+1);
        }
        System.out.print(sb);
    }
}
