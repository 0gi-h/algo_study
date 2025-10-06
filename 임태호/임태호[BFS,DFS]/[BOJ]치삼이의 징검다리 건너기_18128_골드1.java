import java.io.*;
import java.util.*;
public class Main {
    static final int[][] waterD = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
    };
    static final int[][] playerD = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        int[][] wTime = new int[N][N];
        int[][] v = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(wTime[i], Integer.MAX_VALUE);
            Arrays.fill(v[i], Integer.MAX_VALUE);
        }


        int M = Integer.parseInt(st.nextToken());
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            q.offer(new int[]{row, col, 0});
            wTime[row][col] = 0;
        }
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }
        while(!q.isEmpty()){
            int[] cur =  q.poll();
            int row = cur[0];
            int col = cur[1];

            for(int i = 0; i < 4; i++){
                int nR = row + waterD[i][0];
                int nC = col + waterD[i][1];
                if(nR >= 0 && nR < N && nC >= 0 && nC < N && wTime[nR][nC] == Integer.MAX_VALUE){
                    q.offer(new int[]{nR, nC, cur[2] + 1});
                    wTime[nR][nC] = cur[2] + 1;
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        pq.offer(new int[]{0, 0, 0});
        int result = -1;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            if(v[row][col] <= cur[2]) continue;
            v[row][col] = cur[2];
            if(row == N-1 && col == N-1)
            {
                result = cur[2];
                break;
            }
            for(int i = 0; i < 8; i++){
                int nR = row + playerD[i][0];
                int nC = col + playerD[i][1];
                if(nR >= 0 && nR < N && nC >= 0 && nC < N && map[nR][nC] == 1 && Math.max(cur[2], wTime[nR][nC]) < v[nR][nC]){
                    if(nR != N-1 || nC != N-1) pq.offer(new int[]{nR, nC, Math.max(cur[2], wTime[nR][nC])});
                    else pq.offer(new int[]{nR, nC, cur[2]});
                }
            }
        }
        System.out.println(result);
    }
}
