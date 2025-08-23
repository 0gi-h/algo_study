import java.io.*;
import java.util.*;
public class Main {
    static final int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    static int[][] map;
    static int n;
    static int sharkRow;
    static int sharkCol;
    static int fishCount;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        fishCount = 0;
        size = 2;
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkRow = i;
                    sharkCol = j;
                    //중요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 이거 안하면 로직 꼬임
                    map[i][j] = 0;
                }else if(map[i][j] != 0){
                    fishCount++;
                }
            }
        }
        simulate();
    }
    public static void simulate(){
        // int[0] : row , int[1] : col , int[2] : distance
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2] != o2[2]) return o1[2] - o2[2];
                else if(o1[0] != o2[0]) return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        }
        );
        int time = 0;
        int eatCount = 0;
        boolean[][] v;
        // 먹이가 남아 있는 경우
        while(fishCount > 0){
            boolean eat = false;
            if(q.size() > 0){
                q.clear();
            }
            v = new boolean[n][n];
            q.offer(new int[]{sharkRow, sharkCol, 0});
            v[sharkRow][sharkCol] = true;
            while(!q.isEmpty()){
                int[] cur =  q.poll();
                // 먹을 수 있는 경우
                if(map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size){
                    eat = true;
                    map[cur[0]][cur[1]] = 0;
                    if(++eatCount == size){
                        eatCount = 0;
                        size++;
                    }
                    fishCount--;
                    time += cur[2];
                    sharkRow = cur[0];
                    sharkCol = cur[1];
                    break;
                }
                for(int i = 0; i < 4; i++){
                    int row = cur[0] + d[i][0];
                    int col = cur[1] + d[i][1];
                    if (row < 0 || row >= n || col < 0 || col >= n || v[row][col]) continue;
                    if(map[row][col] > size) continue;
                    v[row][col] = true;
                    q.offer(new int[]{row, col, cur[2] + 1});
                }
            }
            // 남은 먹이를 먹을 수 없는 경우
            if(!eat) break;
        }
        System.out.print(time);
    }
}
