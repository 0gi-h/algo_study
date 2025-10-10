import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] map;
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	static ArrayList<Point> chi;
	static ArrayList<Point> home;
	static Point[] sel;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		chi = new ArrayList<>();
		home = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if(map[i][j]==2) {
					chi.add(new Point(i, j));
				}
				else if(map[i][j]==1) {
					home.add(new Point(i, j));
				}
			}
		}
		
		sel = new Point[m];
		recursive(0, 0);
		System.out.println(result);
	}

	private static void recursive(int idx, int k) {
		if(k == m) {
			int temp = find();
			result = result > temp ? temp : result;
			return;
		}
		
		if(idx == chi.size()) {
			return;
		}
		

		sel[k] = chi.get(idx);
		recursive(idx+1, k+1);
		recursive(idx+1, k);
	}

	private static int find() {
		int temp = 0;
		for (int i = 0; i < home.size(); i++) {
			int min = Integer.MAX_VALUE;
			Point cur = home.get(i);
			for (int j = 0; j < m; j++) {
				Point c = sel[j];
				
				int tmp = Math.abs(cur.r - c.r) + Math.abs(cur.c - c.c);
				min = min > tmp ? tmp : min;
			}
			temp += min;
		}
		
		return temp;
	}

}
