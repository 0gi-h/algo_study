import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	static int[] status = {0, 1, 2};
	static int N;
	static int[][] house;
	static boolean[][] visit;
	static int cnt = 0;

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		house = new int[N][N];
		visit = new boolean[N][N];
		visit[0][0] = true;
		visit[0][1] = true;
		int s = status[0];
		
		for(int i = 0; i < N; i++) {
			house[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j = 0; j < N; j++) {
				if(house[i][j] == 1) {
					visit[i][j] = true;
				}
			}
		}
		
		dfs(0, 1, s);
		
		System.out.println(cnt);
		
	}
	
	static void dfs(int i, int j, int s) {
		if(i == N-1 && j == N-1) {
			cnt++;
			return;
		}
		
		if(s == 0) {
			for(int k = 0; k < 2; k++) {
				int nr = i + dr[k];
				int nc = j + dc[k];
				int ns = status[k];
				
				if(ns == 1) {
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false && visit[nr-1][nc] == false && visit[nr][nc-1] == false) {
						dfs(nr, nc, ns);
					}
				}
				
				else if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false) {
					dfs(nr, nc, ns);
				}
			}
		}
		else if(s == 1) {
			for(int k = 0; k < 3; k++) {
				int nr = i + dr[k];
				int nc = j + dc[k];
				int ns = status[k];
				
				if(ns == 1) {
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false && visit[nr-1][nc] == false && visit[nr][nc-1] == false) {
						dfs(nr, nc, ns);
					}
				}
				
				else if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false) {
					dfs(nr, nc, ns);
				}
			}
		}
		else if(s == 2) {
			for(int k = 1; k < 3; k++) {
				int nr = i + dr[k];
				int nc = j + dc[k];
				int ns = status[k];
				
				if(ns == 1) {
					if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false && visit[nr-1][nc] == false && visit[nr][nc-1] == false) {
						dfs(nr, nc, ns);
					}
				}
				
				else if(nr >= 0 && nr < N && nc >= 0 && nc < N && visit[nr][nc] == false) {
					dfs(nr, nc, ns);
				}
			}
		}

	}

}
