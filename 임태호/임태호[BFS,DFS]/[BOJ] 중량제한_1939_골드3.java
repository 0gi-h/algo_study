import java.io.*;
import java.util.*;
public class Main {
    static ArrayList<int[]>[] map;
    static boolean[] v;
    static int sP;
    static int eP;
    public static void main(String[] args) throws   IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int max = 0;
        map = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            map[i] = new  ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken())-1;
            int node2 = Integer.parseInt(st.nextToken())-1;
            int value = Integer.parseInt(st.nextToken());
            max = Math.max(max, value);
            map[node1].add(new int[]{node2, value});
            map[node2].add(new int[]{node1, value});
        }
        v = new boolean[n];
        st  = new StringTokenizer(br.readLine());
        sP =  Integer.parseInt(st.nextToken())-1;
        eP = Integer.parseInt(st.nextToken())-1;
        System.out.println(binary_search(0, max));
    }

    public static int binary_search(int start, int end){
        if(start > end) return end;
        int mid = start + (end-start)/2;
        v = new boolean[v.length];
        v[sP] = true;
        if(dfs(sP, mid)) return binary_search(mid+1, end);
        else return binary_search(start, mid-1);
    }
    public static boolean dfs(int node, int value){
        if(node == eP) return true;
        for(int i = 0; i < map[node].size(); i++){
            int nN = map[node].get(i)[0];
            int nV = map[node].get(i)[1];
            if(nV < value || v[nN]) continue;
            v[nN] = true;
            if(dfs(nN, value)) return true;
        }
        return false;
    }


}
