import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int m;
	static int n;
	static int k;
	static ArrayList<Integer>[] arr;
	static boolean[][] v;
	static int[][] map;
	static int cnt = 0;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Node {
		int r;
		int c;
		
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[k+1];
		v = new boolean[m][n];
		map = new int[m][n];
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 1; i < k+1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new ArrayList<>();
			

			arr[i].add(Integer.parseInt(st.nextToken()));
			arr[i].add(Integer.parseInt(st.nextToken()));
			arr[i].add(Integer.parseInt(st.nextToken()));
			arr[i].add(Integer.parseInt(st.nextToken()));
				
			draw(i);

		}
		
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(!v[i][j]) {
					cnt++;
					result.add(bfs(i, j));
				}

			}
		}
		
		System.out.println(result.size());
		Collections.sort(result);
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

	private static int bfs(int r, int c) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.offer(new Node(r, c));
		v[r][c] = true;
		int tmp = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr >= 0 && nc >= 0 && nr < v.length && nc < v[0].length && !v[nr][nc]) {
					tmp++;
					q.offer(new Node(nr, nc));
					v[nr][nc] = true;
				}
						
			}

		}
		
		return tmp;
	}

	private static void draw(int idx) {
		for(int i = arr[idx].get(1); i < arr[idx].get(3); i++) {
			for(int j = arr[idx].get(0); j < arr[idx].get(2); j++) {
				v[i][j] = true;
			}
		}
		
	}

	
	
}
