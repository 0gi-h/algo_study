import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Fish {
		int r;
		int c;
		int size;
		Fish(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}
	
	static int[][] map;
	static boolean[][] v;
	static ArrayList<Fish> fishes = new ArrayList<>();
	static int n;
	static Fish bs;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		v = new boolean[n][n];
		int sec = 0;

		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				if(tmp != 0 && tmp != 9) {
					map[i][j] = tmp;
					
					fishes.add(new Fish(i, j, tmp));
				}
				
				else if(tmp != 0 && tmp == 9) {
					bs = new Fish(i, j, 2);
				}

			}
		}
		
		if(fishes.size() == 0) {
			System.out.println(0);
		} 
		else {
			
			int fish_cnt = 0;
			int size = fishes.size();
			
			for(int i = 0; i < size; i++) {
				int dis = Integer.MAX_VALUE;
				Fish target = new Fish(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
				
				for (Fish f : fishes) {
					if(f.size < bs.size) {
						v = new boolean[n][n];
						int temp = bfs(f);
						if(temp != 0 && dis > temp) {
								dis = temp ;
								target = f;
						}
						
					}
					
				}
				
				if(dis != Integer.MAX_VALUE) {
					fishes.remove(target);
					bs.r = target.r;
					bs.c = target.c;
					fish_cnt++;
					sec += dis;
					if(fish_cnt == bs.size) {
						bs.size++;
						fish_cnt = 0;
					}
				}
			}
			
			System.out.println(sec);
		}
		
	}

	private static int bfs(Fish f) {
		ArrayDeque<Fish> q = new ArrayDeque<>();
		q.offer(f);
		v[f.r][f.c] = true;
		int cnt = 0;
		
		//bfs 거리 탐색 후 다음 물고기 탐색
		while(!q.isEmpty()) {
			int size = q.size();

			
			for(int step = 0; step < size; step++) {
				Fish cur = q.poll();
				
				
				if(cur.r == bs.r && cur.c == bs.c) {
					
					return cnt;
				}
				
				for(int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					
					if(nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] <= bs.size && !v[nr][nc]) {
						v[nr][nc] = true;
						q.offer(new Fish(nr, nc, cur.size));
					}
				}
			}
			cnt++;


		}
		
		return 0;
	}

}
