import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static class Point{
		int r, c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static Point[] ws;
	static boolean[][] wv;
	static boolean[][] v;
	static int[] wdr = {1, 0, -1, 0};
	static int[] wdc = {0, 1, 0, -1};
	static int[] dr = {1, 0, -1, 0, 1, 1, -1, -1};
	static int[] dc = {0, 1, 0, -1, 1, -1, 1, -1};
	static int n;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		ws = new Point[w];
		map = new int[n+1][n+1];
		wv = new boolean[n+1][n+1];
		wv[n][n] = true;
		wv[1][1] = true;
		
		for (int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			ws[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 1; i <= n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(str[j-1]);
			}
		}
		
		if(map[n-1][n-1] == 0 && map[n-1][n] == 0 && map[n][n-1] == 0) {
			System.out.println(-1);
			return;
		}
		
		
		bfs();
	}

	private static void bfs() {
		ArrayDeque<Point> q = new ArrayDeque<>();
		for (int i = 0; i < ws.length; i++) {
			q.add(ws[i]);
			wv[ws[i].r][ws[i].c] = true; 
		}
		int cnt = 0;
		boolean check = false;
		if(go()) {
			System.out.println(cnt);
			return;
		}
		
		outer: while(!q.isEmpty()) {
			int size = q.size();
			
			
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				
				
				for (int j = 0; j < 4; j++) {
					int nr = cur.r + wdr[j];
					int nc = cur.c + wdc[j];
					
					if(nr >= 1 && nc >= 1 && nr < n+1 && nc < n+1 && !wv[nr][nc] ) {
						q.offer(new Point(nr, nc));
						wv[nr][nc] = true;
					}
				}
			}
			
			cnt++;
			if(wv[n-1][n-1] || wv[n-1][n] || wv[n][n-1]) {
				check = go();
			}
			if(check) {
				System.out.println(cnt);
				return;
			}
		}
		
		System.out.println(-1);
	}

	private static boolean go() {
		ArrayDeque<Point> q = new ArrayDeque<>();
		v = new boolean[n+1][n+1];
		q.offer(new Point(1, 1));
		v[1][1] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.r == n && cur.c == n) {
				return true;
			}
			
			for (int i = 0; i < 8; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr >= 1 && nc >= 1 && nr < n+1 && nc < n+1 && wv[nr][nc] && !v[nr][nc] && map[nr][nc] == 1) {
					q.offer(new Point(nr, nc));
					v[nr][nc] = true;
				}
			}
			
		}
		return false;
	}
}
