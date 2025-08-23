import java.io.*;
import java.util.*;

public class Main {
    static final int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
    static ArrayList<ArrayList<int[]>> islands;
    static int[] root;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        islands = new ArrayList<ArrayList<int[]>>();
        int[][] arr = new int[n][m];
        boolean[][] v = new boolean[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0){
                    v[i][j] = true;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(v[i][j]) continue;
                islands.add(new ArrayList<>());
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int y = cur[0];
                    int x = cur[1];
                    if(v[y][x]) continue;
                    v[y][x] = true;
                    islands.get(islands.size()-1).add(new int[]{y,x});
                    for(int k = 0 ; k < 4; k++){
                        int nextY = y + d[k][0];
                        int nextX = x + d[k][1];
                        if(nextY < 0 || nextY >= n || nextX < 0 || nextX >= m ) continue;
                        if(!v[nextY][nextX]){
                            q.offer(new int[]{nextY, nextX});
                        }
                    }
                }
            }
        }

        root = new int[islands.size()];
        for(int i = 0; i < islands.size(); i++){
            root[i] = i;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new  Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return o1[2] -  o2[2];
            }
        });

        for(int i = 0; i < islands.size(); i++){
            for(int j = i+1; j < islands.size(); j++){
                int index = -1;
                int index2 = -1;
                int min = 11;
                for(int k = 0; k < islands.get(i).size(); k++){
                    for(int l = 0; l < islands.get(j).size(); l++){
                        if(islands.get(i).get(k)[0] == islands.get(j).get(l)[0] &&  (Math.abs(islands.get(i).get(k)[1]- islands.get(j).get(l)[1]) >2)){

                            int start = Math.min(islands.get(i).get(k)[1] , islands.get(j).get(l)[1]);
                            int end = Math.max(islands.get(i).get(k)[1] , islands.get(j).get(l)[1]);
                            boolean poss = true;
                            for(int u = start + 1; u < end; u++){
                                if(arr[islands.get(i).get(k)[0]][u] == 1){
                                    poss = false;
                                    break;
                                }
                            }
                            if(!poss) continue;

                            min = Math.min(min,Math.abs(islands.get(i).get(k)[1]- islands.get(j).get(l)[1])-1);
                            index = i;
                            index2 = j;
                        }
                        if(islands.get(i).get(k)[1] == islands.get(j).get(l)[1] && (Math.abs(islands.get(i).get(k)[0]- islands.get(j).get(l)[0]) > 2)){
                            int start = Math.min(islands.get(i).get(k)[0] , islands.get(j).get(l)[0]);
                            int end = Math.max(islands.get(i).get(k)[0] , islands.get(j).get(l)[0]);
                            boolean poss = true;
                            for(int u = start + 1; u < end; u++){
                                if(arr[u][islands.get(i).get(k)[1]] == 1){
                                    poss = false;
                                    break;
                                }
                            }
                            if(!poss) continue;

                            min = Math.min(min,Math.abs(islands.get(i).get(k)[0]- islands.get(j).get(l)[0])-1);
                            index = i;
                            index2 = j;
                        }
                    }
                }
                if(index != -1 && index2 != -1){
                    pq.offer(new int[]{index, index2, min});
                }
            }
        }
        int sum = 0;
        boolean[] visited = new boolean[islands.size()];
        while(pq.size() > 0){
            int [] cur = pq.poll();
            if(find(cur[0]) == find(cur[1])) continue;

            union(cur[0], cur[1]);
            sum += cur[2];
        }
        for(int i = 0; i < islands.size(); i++){
            root[i] = find(i);
        }
        boolean allConnected = true;
        int now = root[0];
        for(int i = 1; i < islands.size(); i++){
            if(now != root[i]){
                allConnected = false;
                break;
            }
        }
        System.out.println(sum == 0 || !allConnected ? -1 : sum);
    }
    public static void union(int i , int j){
        int rootI =  find(i);
        int rootJ = find(j);
        if(rootI == rootJ) return;
        else if(rootI < rootJ){
            root[rootJ] = rootI;
        }else{
            root[rootI] = rootJ;
        }
    }
    public static int find(int i){
        if(root[i] == i) return i;
        return root[i] =  find(root[i]);
    }

}
