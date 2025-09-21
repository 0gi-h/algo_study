import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n   = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(map[i], '*');
        }

        dfs(0, 0, n ,map);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void dfs(int startRow, int startCol,int size, char[][] map){
        if(size == 1) return;

        int nS = size/3;
        dfs(startRow, startCol, nS, map);
        dfs(startRow, startCol + nS, nS, map);
        dfs(startRow, startCol + nS*2, nS, map);
        dfs(startRow + nS, startCol ,nS, map);
        dfs(startRow + nS, startCol + nS*2, nS, map);
        dfs(startRow + nS*2, startCol, nS, map);
        dfs(startRow + nS*2, startCol + nS, nS, map);
        dfs(startRow + nS*2, startCol + nS*2, nS, map);



        for(int i = startRow + nS ; i < startRow + nS*2; i++){
            for(int j = startCol + nS; j < startCol + nS*2; j++){
                map[i][j] = ' ';
            }
        }


    }

}
