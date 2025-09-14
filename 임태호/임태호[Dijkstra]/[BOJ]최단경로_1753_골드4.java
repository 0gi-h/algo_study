import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine())-1;
        ArrayList<int[]>[] arr = new ArrayList[v];
        int[] d = new int[v];
        boolean[] vis = new boolean[v];
        Arrays.fill(d, 2_000_001);
        for(int i = 0; i < v; i++){
            arr[i] = new ArrayList<>();
        }
        d[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[1], o2[1]);
            }
        });
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken())-1;
            int w  = Integer.parseInt(st.nextToken());
            arr[f].add(new int[]{s, w});
            if(f == start){
                pq.offer(new int[]{s, w});
            }
        }
        vis[start] = true;
        while(pq.size() > 0){
            int[] cur = pq.poll();

            if(vis[cur[0]]) continue;
            vis[cur[0]] = true;
            d[cur[0]] = cur[1];

            for(int i = 0; i < arr[cur[0]].size(); i++){
                int[] next = arr[cur[0]].get(i);
                pq.offer(new int[]{next[0], cur[1] + next[1]});
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < v; i++){
            sb.append(d[i] == 2_000_001 ? "INF" : d[i]).append("\n");
        }
        System.out.println(sb);
    }
}
