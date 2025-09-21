import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D =  Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken())-1;
            int[] dist = new int[N];
            dist[C] = 0;

            ArrayList<int[]>[] map = new ArrayList[N];
            for(int i = 0; i < N; i++){
                map[i] = new ArrayList<>();
            }

            for(int i = 0 ; i < D; i++){
                st = new StringTokenizer(br.readLine());
                int end =  Integer.parseInt(st.nextToken())-1;
                int start = Integer.parseInt(st.nextToken())-1;
                int time =  Integer.parseInt(st.nextToken());
                map[start].add(new int[]{end,time});
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return Integer.compare(o1[1], o2[1]);
                }
            });

            boolean[] visited = new boolean[N];
            int time = 0;
            int count = 0;
            pq.offer(new int[]{C, 0});
            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                if(visited[cur[0]]) continue;
                visited[cur[0]] = true;
                time = cur[1];
                count++;
                for(int i = 0; i < map[cur[0]].size(); i++){
                    pq.offer(new int[]{map[cur[0]].get(i)[0], cur[1]+map[cur[0]].get(i)[1]});
                }
            }
            sb.append(count).append(" ").append(time).append("\n");
        }
        System.out.print(sb);
    }
}
