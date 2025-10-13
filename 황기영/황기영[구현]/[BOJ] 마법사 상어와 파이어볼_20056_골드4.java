import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;

    static class FireBall {
        int r, c, m, s, d;

        FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static ArrayList<FireBall> arr = new ArrayList<>();
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            arr.add(new FireBall(r, c, m, s, d));
        }

        go();
    }

    private static void go() {
        for (int t = 0; t < k; t++) {

            // 1. 이동
            ArrayList<FireBall>[][] grid = new ArrayList[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = new ArrayList<>();
                }
            }

            for (FireBall fb : arr) {
                int nr = (fb.r + dr[fb.d] * (fb.s % n) + n * 1000) % n;
                int nc = (fb.c + dc[fb.d] * (fb.s % n) + n * 1000) % n;
                grid[nr][nc].add(new FireBall(nr, nc, fb.m, fb.s, fb.d));
            }

            // 2. 병합 & 분리
            ArrayList<FireBall> next = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int count = grid[i][j].size();
                    if (count == 0) continue;

                    if (count == 1) {
                        next.add(grid[i][j].get(0));
                    } else {
                        int sumM = 0;
                        int sumS = 0;
                        boolean allEven = true;
                        boolean allOdd = true;

                        for (FireBall fb : grid[i][j]) {
                            sumM += fb.m;
                            sumS += fb.s;
                            if (fb.d % 2 == 0) allOdd = false;
                            else allEven = false;
                        }

                        int newM = sumM / 5;
                        if (newM == 0) continue;

                        int newS = sumS / count;
                        
                        if(allEven || allOdd) {
                        	next.add(new FireBall(i, j, newM, newS, 0));
                        	next.add(new FireBall(i, j, newM, newS, 2));
                        	next.add(new FireBall(i, j, newM, newS, 4));
                        	next.add(new FireBall(i, j, newM, newS, 6));
                        }
                        else {
                        	next.add(new FireBall(i, j, newM, newS, 1));
                        	next.add(new FireBall(i, j, newM, newS, 3));
                        	next.add(new FireBall(i, j, newM, newS, 5));
                        	next.add(new FireBall(i, j, newM, newS, 7));
                        }
                    }
                }
            }

            arr = next;
        }

        int total = 0;
        for (FireBall fb : arr) {
            total += fb.m;
        }

        System.out.println(total);
    }
}
