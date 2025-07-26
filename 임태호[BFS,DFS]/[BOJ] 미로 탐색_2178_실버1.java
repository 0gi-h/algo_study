// 여기에 코드 작성
import java.io.*;
import java.util.*;
public class Main{
    public static int[] dR = {1, 0 , -1, 0};
    public static int[] dC = {0, 1 , 0, -1};
    public static int[][] map;
    public static boolean[][] visited;
    public static int r;
    public static int c;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c=  Integer.parseInt(st.nextToken());

        map = new int[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++){
            String s= br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.print(bfs());
    }
    public static int bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        while(queue.size() > 0){
            int[] now = queue.poll();
            int nR = now[0];
            int nC = now[1];
            int count = now[2];

            if(nR == r-1 && nC == c-1) return count;

            for(int i = 0; i < 4; i++){
                int nnR = nR + dR[i];
                int nnC = nC + dC[i];
                if(check(nnR, nnC)) continue;
                if(map[nnR][nnC] == 0) continue;
                if(visited[nnR][nnC]) continue;
                visited[nnR][nnC] = true;
                queue.offer(new int[]{nnR, nnC, count+1});
            }

        }
        return -1;
    }
    public static boolean check(int col, int row){
        if(col >= 0 && col < r && row >= 0 && row < c) return false;
        return true;
    }
}
