import java.util.*;
import java.io.*;
public class Main {
    static final int[][] d = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };
    static class FireBall{
        int r;
        int c;
        int m;
        int s;
        int d;
        FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N;
    static int K;
    //격자판
    //row, col, (0 : 개수, 1 : 질량합, 2 : 속력 합, 3 : 방향 플래그, 4 : 방향
    static int[][][] map;
    //id 값과 FireBall
    static Queue<FireBall> fireBalls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N][5];
        fireBalls  = new ArrayDeque<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int row =  Integer.parseInt(st.nextToken())-1;
            int col =  Integer.parseInt(st.nextToken())-1;
            int m =   Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d =   Integer.parseInt(st.nextToken());
            FireBall fb = new FireBall(row,col,m,s,d);
            map[row][col][0]++;
            map[row][col][1] += m;
            map[row][col][2] += s;
            map[row][col][3] += (d % 2 == 0) ? 1 : -1;
            map[row][col][4] += d;

            fireBalls.offer(fb);
        }


        for(int i = 0; i < K; i++){
            simulate();
        }

        int sum = 0;
        while(!fireBalls.isEmpty()) sum += fireBalls.poll().m;
        System.out.println(sum);
    }
    public static void simulate(){
        while(fireBalls.size() > 0){
            FireBall fb = fireBalls.poll();
            map[fb.r][fb.c][0]--;
            map[fb.r][fb.c][1]-= fb.m;
            map[fb.r][fb.c][2]-= fb.s;
            map[fb.r][fb.c][3] -= fb.d % 2 == 0 ? 1 : -1;
            map[fb.r][fb.c][4] -= fb.d;
            int nR = (fb.r + d[fb.d][0] * (fb.s % N) + N) % N;
            int nC = (fb.c + d[fb.d][1] * (fb.s % N) + N) % N;
            map[nR][nC][0] ++;
            map[nR][nC][1] += fb.m;
            map[nR][nC][2] += fb.s;
            map[nR][nC][3] += fb.d % 2 == 0 ? 1 : -1;
            map[nR][nC][4] += fb.d;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N ;j++){
                if(map[i][j][0] == 0) continue;
                else if(map[i][j][0] == 1) fireBalls.offer(new FireBall(i, j, map[i][j][1] , map[i][j][2], map[i][j][4]));
                else{
                    int nm = map[i][j][1] / 5;
                    if (nm == 0) {
                        for (int d = 0; d < 5; d++) {
                            map[i][j][d] = 0;
                        }
                        continue;
                    }
                    int ns = map[i][j][2] / map[i][j][0];
                    int startD = (map[i][j][0] == Math.abs(map[i][j][3])) ? 0 : 1;

                    for(int k = 0; k < 4; k++){
                        fireBalls.offer(new FireBall(i, j, nm, ns, startD + 2 * k));
                    }

                    map[i][j][0] = 4;
                    map[i][j][1] = nm * 4;
                    map[i][j][2] = ns * 4;
                    map[i][j][3] = 4 * (startD == 0 ? 1 : -1);
                    map[i][j][4] = startD == 0 ? 12 : 16;
                }
            }
        }
    }
}
