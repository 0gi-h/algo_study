import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][] wallCondition = new boolean[N][M];
        int[][] time = new int[N][M];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[2], o2[2]);
            }
        });
        for(int i = 0; i < N; i++){
            Arrays.fill(time[i] , Integer.MAX_VALUE);
            String s  = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'S'){
                    pq.offer(new int[]{i, j , 0});
                    time[i][j] = 0;
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != '#'){
                    for(int k = 0 ; k < 4; k++){
                        int nI = i + d[k][0];
                        int nJ = j + d[k][1];
                        if(nI < 0 || nI >= N || nJ < 0 || nJ >= M)continue;
                        if(map[nI][nJ] == '#'){
                            wallCondition[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        while(pq.size() > 0){
            int[] cur =  pq.poll();
            int row = cur[0];
            int col = cur[1];
            int t = cur[2];
            if(map[row][col] == 'E'){
                System.out.println(t);
                return;
            }

            for(int i = 0; i < 4; i++){
                int nR = row + d[i][0];
                int nC = col + d[i][1];
                if(nR < 0 || nR >= N || nC < 0 || nC >= M || map[nR][nC] == '#' || time[nR][nC] <= t) continue;
                if(wallCondition[nR][nC] && wallCondition[row][col]){
                    pq.offer(new int[]{nR, nC, t });
                    time[nR][nC] = t;
                }else{
                    if(time[nR][nC] > t+1) {
                        pq.offer(new int[]{nR, nC, t + 1});
                        time[nR][nC] = t + 1;
                    }
                }
            }
        }
    }
}
