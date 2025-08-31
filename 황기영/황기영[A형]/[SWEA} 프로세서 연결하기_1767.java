import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1767 {

    static class Pro {
        int r, c;
        boolean[] dir;

        Pro(int r, int c) {
            this.r = r;
            this.c = c;
            this.dir = new boolean[] {true, true, true, true}; //위, 아래, 왼, 오
        }
    }

    static int[][] map;
    static boolean[][] v;
    static ArrayList<Pro> processes;
    static int[] dr = {-1, 1, 0, 0}; //위, 아래, 왼, 오
    static int[] dc = {0, 0, -1, 1};
    static int n;
    static int minWireLength = Integer.MAX_VALUE;
    static int maxConnected = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            v = new boolean[n][n];
            processes = new ArrayList<>();
            minWireLength = Integer.MAX_VALUE;
            maxConnected = -1;

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                //맵과 방문배열 처리
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) {
                        v[i][j] = true;
                        //프로세서 위치 저장
                        if(i == 0 || j == 0 || i == n-1 || j == n-1) {
                            continue;
                        }
                        else {
                            processes.add(new Pro(i, j));
                        }
                    }
                }
            }

            research(0, 0, 0, v);

            System.out.println("#" + tc + " " + minWireLength);
        }
    }

    private static void research(int idx, int wireLength, int connected, boolean[][] visited) {

        if(idx == processes.size()) {
            // 1순위: 더 많은 프로세서를 연결한 경우
            if(connected > maxConnected) {
                maxConnected = connected;
                minWireLength = wireLength;
            }
            // 2순위: 같은 개수를 연결했을 때 더 적은 전선 길이
            else if(connected == maxConnected) {
                minWireLength = Math.min(minWireLength, wireLength);
            }
            return;
        }

        Pro current = processes.get(idx);
        check(current);

        // 1. 현재 프로세서를 연결하지 않는 경우
        research(idx + 1, wireLength, connected, visited);

        // 2. 현재 프로세서를 각 방향으로 연결하는 경우
        for (int i = 0; i < 4; i++) {
            if(current.dir[i] == true) {
                // 방문 배열 복사본 생성
                boolean[][] newVisited = copyArray(visited);
                int tmp = dfs(current, i, newVisited, 0);
                if(tmp != 0) {
                    research(idx + 1, wireLength + tmp, connected + 1, newVisited);
                }
            }
        }
    }

    private static int dfs(Pro p, int idx, boolean[][] visited, int len) {
        
        // 현재 위치가 경계인지 확인
        if(p.r == 0 || p.r == n-1 || p.c == 0 || p.c == n-1) {
            return len; // 경계 도달시 현재까지의 길이 반환
        }
        
        // 현재 위치 방문 표시 (프로세서가 아닌 전선 경로만)
        if(len > 0) {  // 시작 프로세서 위치는 방문 표시하지 않음
            visited[p.r][p.c] = true;
        }
        
        int nr = p.r + dr[idx];
        int nc = p.c + dc[idx];

        // 범위를 벗어나거나 이미 방문한 곳이면 실패
        if(nr < 0 || nc < 0 || nr >= n || nc >= n || visited[nr][nc]) {
            return 0;
        }

        // 재귀 호출
        return dfs(new Pro(nr, nc), idx, visited, len + 1);
    }

    // 방문 배열 복사
    private static boolean[][] copyArray(boolean[][] original) {
        boolean[][] copy = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    private static void check(Pro p) {
        
        for (Pro np : processes) {
            if(p == np) continue; // 자기 자신은 제외
            
            if(p.r == np.r && p.c > np.c) {
                p.dir[2] = false; // 왼쪽 차단
            }
            else if(p.r == np.r && p.c < np.c) {
                p.dir[3] = false; // 오른쪽 차단
            }
            else if(p.c == np.c && p.r > np.r) {
                p.dir[0] = false; // 위쪽 차단
            }
            else if(p.c == np.c && p.r < np.r) {
                p.dir[1] = false; // 아래쪽 차단
            }
        }
    }
}
