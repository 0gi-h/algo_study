import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node{
		int to, weight;
		Node next;
		
		Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex> {
		int no, dis;
		
		Vertex(int no, int dis) {
			this.no = no;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.dis, o.dis);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		Node[] map = new Node[v+1];
		int[] dist = new int[v+1];
		boolean[] visited = new boolean[v+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[from] = new Node(to, weight, map[from]);
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Vertex(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if(visited[cur.no]) continue;
			visited[cur.no]= true; 
		
			
			for (Node temp = map[cur.no]; temp != null; temp = temp.next) {
				if(!visited[temp.to] && dist[temp.to] > cur.dis + temp.weight) {
					dist[temp.to] = cur.dis + temp.weight;
					pq.offer(new Vertex(temp.to, dist[temp.to]));
				}
			}
		
		
		
		}

		for (int i = 1; i <= v; i++) {
			System.out.println(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF");
		}
	}
}
