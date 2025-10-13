import java.io.*;
import java.util.*;
public class Main {
    public static class Pos {
        int nowRow;
        int nowCol;
        int preRow;
        int preCol;
        int time;
        public Pos(int nowRow, int nowCol, int preRow, int preCol, int time){
            this.nowRow = nowRow;
            this.nowCol = nowCol;
            this.preRow = preRow;
            this.preCol = preCol;
            this.time = time;
        }
    }
    public static int[][] d = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        
        int startRow = 0;
        int startCol = 0;
        int endRow = 0;
        int endCol = 0;
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                switch (s.charAt(j)){
                    case 'H' : {
                        endRow = i;
                        endCol = j;
                        map[i][j] = 0;
                        break;
                    } 
                    case 'X' : {
                        map[i][j] = -1;
                        break;
                    }
                    case 'S' : {
                        map[i][j] = 0;
                        startRow = i;
                        startCol = j;
                        break;
                    }
                    default  : {
                        map[i][j] = s.charAt(j) -'0';
                        break;
                    }

                }
            }
        }
        Queue<Pos> queue = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        queue.offer(new Pos(startRow, startCol, startRow, startCol, 0));

        while(queue.size() > 0){
            Pos now = queue.poll();
            int pp = map[now.preRow][now.preCol];
            int p = map[now.nowRow][now.nowCol];
            for(int i = 0; i < 4; i++){

                int nR = now.nowRow + d[i][0];
                int nC = now.nowCol + d[i][1];
                if(nR < 0 || nC < 0 || nR >= N || nC >= M || map[nR][nC] == -1 || (nR == now.preRow && nC == now.preCol)) continue;
                if( (visited[nR][nC] & (1 << i)) != 0) continue;
                if(nR == endRow && nC == endCol){
                    System.out.println(now.time + 1);
                    return;
                }
                
                int nV = map[nR][nC];
                if(pp + p + nV > K) continue;

                visited[nR][nC] |= (1 << i);
                queue.offer(new Pos(nR, nC, now.nowRow, now.nowCol, now.time+1));

            }
        }
        System.out.println(-1);
        
    }
}
