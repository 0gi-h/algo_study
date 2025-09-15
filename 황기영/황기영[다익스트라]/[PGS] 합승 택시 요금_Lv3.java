import java.util.*;

class Solution {
    
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
    
    static int[][] dist;
    static Node[] map;
    static boolean[] v;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        map = new Node[n+1];
        
        for(int i = 0; i < fares.length; i++) {
            map[fares[i][0]] = new Node(fares[i][1], fares[i][2], map[fares[i][0]]);
            map[fares[i][1]] = new Node(fares[i][0], fares[i][2], map[fares[i][1]]);
        }
        
        dist = new int[3][n+1];

        
        for(int i = 0; i < 3; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
             
        dist[0][0] = s;
        dist[1][0] = a;
        dist[2][0] = b;
        
        for(int i = 0; i < 3; i++) {
            v = new boolean[n+1];
            dijk(i, dist[i][0]);
        }
        
        
        for(int i = 1; i <= n; i++) {
            int temp = 0;
            temp += dist[0][i] + dist[1][i] + dist[2][i];
            answer = Math.min(answer, temp);
        }
        
        return answer;
    }
    
    static void dijk(int idx, int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
		dist[idx][start] = 0;
		pq.offer(new Vertex(start, dist[idx][start]));
        
        while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if(v[cur.no]) continue;
			v[cur.no] = true;
			
			for(Node temp = map[cur.no]; temp != null; temp = temp.next)  {
				if(!v[temp.to] && dist[idx][temp.to] > cur.dis + temp.weight) {
					dist[idx][temp.to] = cur.dis + temp.weight;
					pq.offer(new Vertex(temp.to, dist[idx][temp.to]));
				}
			}
		}
        
    }
}
