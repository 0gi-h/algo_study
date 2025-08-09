import java.util.*;
import java.io.*;
class Main {
    public static class Road implements Comparable<Road> {
        int end;
        int cost;
        public Road(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Road o){
            return this.cost > o.cost ? 1 : this.cost < o.cost ? -1 : this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        int m = readInt();
        int k = readInt();
        int x = readInt();
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i =0; i<n; i++){
            map.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++){
            int start = readInt();
            int end = readInt();
            map.get(start-1).add(end-1);
        }
        PriorityQueue<Road> pq = new PriorityQueue<>();
        boolean[] v = new boolean[n];
        v[x-1] = true;
        for(int i = 0; i < map.get(x-1).size(); i++){
            pq.offer(new Road(map.get(x-1).get(i), 1));
        }

        while(pq.size() > 0){
            Road now = pq.poll();
            int end = now.end;
            if(v[end]) continue;
            v[end] = true;
            int cost = now.cost;
            if(cost > k) break;
            if(cost == k) sb.append(end+1).append("\n");

            for(int i = 0; i< map.get(end).size(); i++){
                if(v[map.get(end).get(i)]) continue;
                pq.offer(new Road(map.get(end).get(i), cost+1));
            }
        }
        System.out.println(sb.length() == 0 ? -1 : sb);
    }
    public static int readInt() throws IOException {
        int val;
        int total = 0;
        while((val = System.in.read()) > 32){
            total= total*10 + val- '0';
        }
        return total;
    }
}
