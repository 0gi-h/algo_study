import java.util.*;
class Solution {
    static ArrayList<int[]>[] map;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 0 : 출발 지점에서 , 1 : A 지점에서, 2 : B 지점에서
        map = new ArrayList[n];
        for(int i = 0; i < n; i++){
            map[i] = new ArrayList<>();
        }
        int[] point = {s-1, a-1, b-1};
        for(int i = 0; i < fares.length; i++){
            int n1 = fares[i][0]-1;
            int n2 = fares[i][1]-1;
            int weight = fares[i][2];
            map[n1].add(new int[]{n2,weight});
            map[n2].add(new int[]{n1,weight});
        }
        int[][] dist = new int[3][n];
        for(int i = 0; i < 3; i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dijkstra(dist, i, point);
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            min = Math.min(min, dist[0][i] + dist[1][i] + dist[2][i]);
        }
        return min;
    }
    public void dijkstra(int[][] dist, int index, int[] point){
        dist[index][point[index]] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[1], o2[1]);
            }
        });
        boolean[] visited = new boolean[dist[0].length];
        pq.offer(new int[]{point[index], 0});
        while(pq.size() > 0){
            int[] cur = pq.poll();
            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;
            dist[index][cur[0]] = cur[1];

            for(int i = 0; i < map[cur[0]].size(); i++){
                pq.offer(new int[]{ map[cur[0]].get(i)[0],map[cur[0]].get(i)[1] + cur[1] });
            }
        }
    }
}
