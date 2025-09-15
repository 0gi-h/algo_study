import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node{
		int to, time;
		Node next;
		
		Node(int to, int time, Node next) {
			this.to = to;
			this.time = time;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		int no, tTime;
		
		Vertex(int no, int tTime) {
			this.no = no;
			this.tTime = tTime;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.tTime, o.tTime);
		}
	}
	
	static Node[] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map = new Node[n+1];
			int[] dist = new int[n+1];
			boolean[] v = new boolean[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			for (int i = 1; i <= d; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				map[from] = new Node(to, time, map[from]);
			}
			

			
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			dist[c] = 0;
			pq.offer(new Vertex(c, dist[c]));
			
			
			while(!pq.isEmpty()) {
				Vertex cur = pq.poll();
				if(v[cur.no]) continue;
				v[cur.no] = true;
				
				for (Node temp = map[cur.no]; temp != null; temp = temp.next) {
					if(!v[temp.to] && dist[temp.to] > cur.tTime + temp.time) {
						dist[temp.to] = cur.tTime + temp.time;
						pq.offer(new Vertex(temp.to, dist[temp.to]));
					}
				}
			}
			
			int cnt = 0;
			int max = 0;
			for (int i = 1; i <= n; i++) {
				if(dist[i] != Integer.MAX_VALUE) {
					cnt++;
					max = Math.max(max, dist[i]);
				}
			}

			System.out.println(cnt + " " + max);
		}
	}

}
