import java.io.*;
import java.util.*;
public class BatteryCharge {
    static int[][] d = {
        {1,0},
        {0,1},
        {-1,0},
        {0, -1}
    };
    public static void main(String[] args) throws IOException{
        // System.setIn(new FileInputStream("C:\\ssafy\\OFFLINE\\02.algorithm\\hamburger\\input.txt"));
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= t; test_case++){
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            for(int i = 0; i < n; i++){
                st= new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[][] v = new boolean[n][n];
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[]o1, int[]o2){
                    return o1[2] - o2[2];
                }
            });
            pq.offer(new int[]{0,0,0});

            int result = -1;
            while(pq.size()>0){
                int[] cur = pq.poll();
                int height = map[cur[0]][cur[1]];
                if(cur[0] == n-1 && cur[1] == n-1){
                    result = cur[2];
                    break;
                }
                if(v[cur[0]][cur[1]]) continue;
                v[cur[0]][cur[1]] = true;

                for(int i = 0; i < 4; i++){
                    int nextRow = cur[0] + d[i][0];
                    int nextCol = cur[1] + d[i][1];
                    if(nextRow >= n || nextCol >= n || nextRow < 0 || nextCol < 0 || v[nextRow][nextCol]) continue;
                    int nextHeight = map[nextRow][nextCol];
                    if(nextHeight == height){
                        pq.offer(new int[]{nextRow, nextCol, cur[2] + 1});
                    }else if(nextHeight > height){
                        pq.offer(new int[]{nextRow, nextCol, cur[2] + 2*(nextHeight - height)});
                    }else{
                        pq.offer(new int[]{nextRow, nextCol, cur[2]});
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
        
    }
}
