import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1939 {
	
	static class Node{
		int to, weight;
		Node next;
		
		Node (int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static class Vertex implements Comparable<Vertex> {
		int no, total;
		
		Vertex(int no, int total) {
			this.no = no;
			this.total = total;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(o.total, this.total);
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Node[] map = new Node[n+1];
		boolean[] v = new boolean[n+1];
		int[] dist = new int[n+1];
		int[] mw = new int[n+1];
		Arrays.fill(dist, 0);
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			map[from] = new Node(end, weight, map[from]);
			map[end] = new Node(from, weight, map[end]);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dist[start] = Integer.MAX_VALUE;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, dist[start]));
		
		 while (!pq.isEmpty()) {
	            Vertex cur = pq.poll();

	            if (v[cur.no]) continue;
	            v[cur.no] = true;

	            if (cur.no == end) {
	            	System.out.println(cur.total);;
	            	return;
	            }

	            for (Node temp = map[cur.no]; temp != null; temp = temp.next) {
	                if (!v[temp.to]) {
	                    int minWeight = Math.min(cur.total, temp.weight);
	                    if (dist[temp.to] < minWeight) {
	                        dist[temp.to] = minWeight;
	                        pq.offer(new Vertex(temp.to, dist[temp.to]));
	                    }
	                }
	            }
	        }
	}

}
