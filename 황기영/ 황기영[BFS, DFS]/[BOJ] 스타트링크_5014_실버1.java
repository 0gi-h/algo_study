import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	static int f;
	static int s;
	static int g;
	static int[] e = new int[2];
	static boolean[] v;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		e[0] = Integer.parseInt(st.nextToken());
		e[1] = -Integer.parseInt(st.nextToken());
		
		v = new boolean[f+1];

		
		if(bfs(s) != 0) {
			System.out.println(cnt);
		}
		else {
			System.out.println("use the stairs");
		}
		
	}

	private static int bfs(int s) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(s);
		v[s] = true;
		cnt = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();

			for(int tmp = 0; tmp < size; tmp++) {
				int cur = q.poll(); //이 코드의 위치를 잘 맞춰주자
				
				if(cur == g) {
					return cnt;
				}
				
				for(int i = 0; i < 2; i++) {
					if(cur + e[i] <= f && cur + e[i] >= 1 &&!v[cur + e[i]]) {
						q.offer(cur+e[i]);
						v[cur + e[i]] = true;
					}
				}
			}
			cnt++;
		}
		
		return 0;
	}

}
