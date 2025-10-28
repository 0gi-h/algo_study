import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static String[][] map;
    
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static boolean[][][] visited; 

    static class Point {
        int r, c;       
        int dir;       
        int prevBad;  
        int curBad;   
        int cnt;    

        Point(int r, int c, int dir, int prevBad, int curBad, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.prevBad = prevBad;
            this.curBad = curBad;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new String[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split("");
        }

        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j].equals("S")) {
                    bfs(i, j);
                    break outer;
                }
            }
        }
    }

    private static void bfs(int sr, int sc) {
        visited = new boolean[n][m][4];
        ArrayDeque<Point> q = new ArrayDeque<>();

        q.add(new Point(sr, sc, -1, 0, 0, 0));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (map[cur.r][cur.c].equals("H")) {
                System.out.println(cur.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc].equals("X") || visited[nr][nc][i]) continue;

                if (cur.dir != -1 && (cur.dir + 2) % 4 == i) continue;

                String val = map[nr][nc];
                int nextBad = (val.equals("S") || val.equals("H")) ? 0 : Integer.parseInt(val);

                int totalBad = cur.prevBad + cur.curBad + nextBad;
                if (totalBad > k) continue;

                visited[nr][nc][i] = true;
                q.add(new Point(nr, nc, i, cur.curBad, nextBad, cur.cnt + 1));
            }
        }
        System.out.println(-1);
    }
}
