import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        int index= 1;
        while( (n = Integer.parseInt(br.readLine())) !=0){
            StringTokenizer st;
            int[][] map = new int[n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return o1[2] - o2[2];
                }
            });
            boolean[][] v= new boolean[n][n];
            pq.offer(new int[]{0, 0, map[0][0]});
            int result = -1;
            while(pq.size() > 0){
                int[] cur = pq.poll();
                if(cur[0] == n-1 && cur[1] == n-1){
                    result = cur[2];
                    break;
                }

                if(v[cur[0]][cur[1]]) continue;
                v[cur[0]][cur[1]] = true;

                for(int i  = 0 ; i < 4; i++){
                    int nextRow = cur[0] + d[i][0];
                    int nextCol = cur[1] + d[i][1];

                    if(nextRow >= n || nextCol >= n || nextRow < 0 || nextCol < 0 || v[nextRow][nextCol]) continue;
                    pq.offer(new int[]{nextRow, nextCol, cur[2] + map[nextRow][nextCol]});

                }
            }
            System.out.println("Problem " + index++ +": " +  result);
        }
    }
}
