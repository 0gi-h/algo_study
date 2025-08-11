import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int M;
	static boolean[] v;
	static int result = -1;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		v = new boolean[N + 1];
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			map[s][e] = map[s][e] > t ? map[s][e] : t;
		}
		
		
		recursive(0, 0, 0);
		System.out.println(result);
	}
	
	static void recursive(int start, int dis, int cnt) {
		if(cnt == N) {
			if(map[start][0] > 0) {
				result = Math.max(result,  dis + map[start][0]);
			}
			return;
		}
        for (int i = 1; i <= N; i++) {
            if (!v[i] && map[start][i] > 0) {
                v[i] = true;
                recursive(i, dis + map[start][i], cnt + 1);
                v[i] = false;
            }
        }
	}

}
