import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n =Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		//단방향이라 크기를 조절해도 될 것 같음
//		int[][] map = new int[n+1][n+1];
		ArrayList<Integer>[] map = new ArrayList[n+1]; 
		int[] d = new int[n+1];
		Arrays.fill(d,  Integer.MAX_VALUE);
		boolean[] v = new boolean[n+1];
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 1; i < n+1; i++) {
			map[i] = new ArrayList<>();
		}

		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a].add(b);
		}
		
		
		d[x] = 0;
		PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		q.offer(new int[] {x, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int now = cur[0];
			int dist = cur[1];
			
			if(v[now]) {
				continue;
			}
			
			v[now] = true;
			
			for(int next : map[now]) {
				if(!v[next] && d[next] > d[now]+1) {
					d[next] = d[now]+1;
					q.offer(new int[] {next, d[next]});
				}
			}
			
		}
		
		for(int i = 1; i < n+1; i++) {
			if(d[i] == k) {
				result.add(i);
				System.out.println(i);
			}
		}
		
		if(result.size() == 0) {
			System.out.println(-1);
		}

	}

}
