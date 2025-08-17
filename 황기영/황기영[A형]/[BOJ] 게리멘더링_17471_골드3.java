import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17471 {
 
    static int n;
    static int[] p, area;
    static ArrayList<Integer>[] arr;
    static int min = Integer.MAX_VALUE;
    static boolean[] v;
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
        
        p = new int[n + 1];
        arr = new ArrayList[n + 1];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
            p[i] = Integer.parseInt(st.nextToken());
        }
 
        for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < num; j++) {
				int a = Integer.parseInt(st.nextToken());
				arr[i].add(a);
//				arr[a].add(i);
			}
		}
 
        area = new int[n + 1]; //각 지역구가 속한 선거구 저장. 1 또는 2
        dfs(1); //뽑을 수 있는 모든 지역구를 뽑는 dfs탐색
 
        if(min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    } 
 
    public static void dfs(int k) {
        if(k == n + 1) { //모든 지역 다 뽑았다면
            int area1 = 0;
            int area2 = 0;
            for(int i = 1; i <= n; i++) {
                if(area[i] == 1) area1 += p[i];
                else area2 += p[i];
            }
            
            v = new boolean [n + 1];
            int link = 0; // 연결된 지역들의 개수를 셈.
            for(int i = 1; i <= n; i++) {
                if(!v[i]) {
                    bfs(i, area[i]); //연결된 지역들을 모두 방문표시하는 bfs탐색
                    link++;
                }
            }
            //지역이 2개뤄 나눠졌고, 2지역안에서 서로 연결되어 있다면 최소값 계산
            if(link == 2) min = Math.min(min, Math.abs(area1 - area2)); 
 
            return;
        } 
 
        area[k] = 1; //k지역 1번 선거구에 할당.
        dfs(k + 1);
 
        area[k] = 2; //k지역 2번 선거구에 할당.
        dfs(k + 1);
    }
 
    public static void bfs(int idx, int areaNum) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        v[idx] = true;
        q.offer(idx);
 
        while(!q.isEmpty()) {
            int current = q.poll();
            
            for(int i = 0; i < arr[current].size(); i++) {
                int next = arr[current].get(i);
                if(area[next] == areaNum && !v[next]) {
                    q.offer(next);
                    v[next] = true;
                }
            }
        }
    }
}
