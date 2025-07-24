import java.io.*;
import java.util.*;
public class Main{
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int[] dCol = {1, 0, -1, 0};
    static int[] dRow = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        StringTokenizer st;
        int maxNum = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(map[i][j] , maxNum);
            }
        }
        int max = 0;
        for(int water = 0; water <= maxNum ; water++){
            visited = new boolean[n][n];
            int count = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(map[i][j] <= water){ 
                        visited[i][j] = true;
                        continue;
                    }
                    else if(visited[i][j] == true) continue;
                    bfs(i, j, water);
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        System.out.print(max);
    }

    public static void bfs(int col, int row, int water){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{col,row});
        visited[col][row] = true;
        while(queue.size() > 0){
            int[] now = queue.poll();
            int nCol = now[0];
            int nRow = now[1];
            for(int i =0; i < 4; i++){
                int nnCol = nCol + dCol[i];
                int nnRow = nRow +dRow[i];
                if(nnCol < 0 || nnRow < 0 || nnCol == n || nnRow == n) continue;
                if(map[nnCol][nnRow] <= water){
                    visited[nnCol][nnRow] = true;
                    continue;
                }
                if(map[nnCol][nnRow] > water && !visited[nnCol][nnRow]){
                    visited[nnCol][nnRow] = true;
                    queue.offer(new int[]{nnCol, nnRow});
                }
            }
        }
    }
}
