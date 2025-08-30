import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
        { 0, 1 },
        { 1, 0 },
        { 0, -1},
        { -1, 0}
    };
    static int[][] map;
    static boolean[][] v;
    static int n;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t = 1; t <= T; t++){
            sb.append("#").append(t).append(" "); 
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            map = new int[n][n];
            v = new boolean[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0;
            ArrayList<int[]> arr = new ArrayList<>();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(max < map[i][j]){
                        arr = new ArrayList<>();
                        max = map[i][j];
                        arr.add(new int[]{i,j});
                    }else if(max == map[i][j]){
                        arr.add(new int[]{i, j});
                    }
                }
            }
            result = 0;
            for(int i = 1; i <= k; i++){
                for(int row = 0; row < n; row++){
                    for(int col = 0; col < n; col++){
                        map[row][col] -= i;
                        for(int roop = 0; roop < arr.size(); roop++){
                            v[arr.get(roop)[0]][arr.get(roop)[1]] = true;
                            dfs(arr.get(roop)[0], arr.get(roop)[1], 1);
                            v[arr.get(roop)[0]][arr.get(roop)[1]] = false;
                        }
                        map[row][col] += i;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
    public static void dfs(int row, int col, int count){
        
        boolean check = false;
        for(int i = 0; i < 4; i++){
            int nR = row + d[i][0];
            int nC = col + d[i][1];
            if(nR >= n || nC >= n || nR < 0 || nC < 0 || v[nR][nC]) continue;
            if(map[row][col] <= map[nR][nC]) continue;
            v[nR][nC] = true;
            check = true;
            dfs(nR, nC, count+1);
            v[nR][nC] = false;
        }
        if(!check){
            result = Math.max(count, result);
        }
    }
}
