package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Q1949_2 {
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dc = { 0, 0, -1, 1 };
	static int n, k, ans;
	static int[][] m;
	static boolean[][] v;
	
	static class Height {
		int r, c;
		
		Height(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static ArrayList<Height> h = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			int high = 0;
			ans = 0;
			m = new int[n][n];
			v = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < n; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
					
					if(high == m[i][j]) {
						h.add(new Height(i, j));
					}
					else if(high < m[i][j]) {
						high = Math.max(high, m[i][j]);
						h.clear();
						h.add(new Height(i, j));
					}
					
				}
			}
			
			for (Height he : h) {
				dfs(he, 1, true);
			}
			
			System.out.println("#" + tc + " " + ans);
		}

	}

	private static void dfs(Height he, int dist, boolean isP) {
		
		if(dist > ans) {
			ans = dist;
		}
		
		v[he.r][he.c]= true; 
		
		for (int i = 0; i < 4; i++) {
			int nr = he.r + dr[i];
			int nc = he.c + dc[i];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || v[nr][nc]) {
                continue;
			}
			
			if(m[he.r][he.c] > m[nr][nc]) {
				dfs(new Height(nr, nc), dist+1, isP);
			}
			else if(isP && m[he.r][he.c] > m[nr][nc]-k) {
				int tmp = m[nr][nc];
				m[nr][nc] = m[he.r][he.c] - 1;
				dfs(new Height(nr, nc), dist+1, false);
				m[nr][nc] = tmp;
			}
		}
		v[he.r][he.c] = false;
	}

}
