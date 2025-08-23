import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17406 {

	static int n;
	static int m;
	static int k;
	static int[][] origin;
	static int[][] arr;
	static int[][] marr;
	static ArrayList<int[]> temp;
	static int[] array;
	static int[] sel;
	static boolean[] v;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		origin = new int[n][m];
		arr = new int[n][m];
		marr = new int[n][m];
		for(int i = 0; i < n; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				int a = Integer.parseInt(st.nextToken());
				origin[i][j] = a;
				arr[i][j] = a;
				marr[i][j] = a;
			}
		}
		
		temp = new ArrayList<>();
		array = new int[k];
		sel = new int[k];
		v = new boolean[k];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			temp.add( new int[] {r, c, s});
		}

		for(int i = 0; i < k; i++) {
			recursive(0);
			calc(temp.get(i)[0], temp.get(i)[1], temp.get(i)[2]);
		}
		
		System.out.println(min);
		
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
	}

	//연산 순서 선정
	private static void recursive(int idx) {
		if(idx == temp.size()) {
			//연산 수행 배열들 초기화
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					arr[i][j] = origin[i][j];
					marr[i][j] = origin[i][j];
				}
			}
			
			//뽑은 순열 순서로 연산 진행
			for(int i = 0; i < array.length; i++) {
				calc(temp.get(sel[i])[0], temp.get(sel[i])[1], temp.get(sel[i])[2]);
			}
			
			
			for(int i = 0; i < n; i++) {
				int tmp = 0;
				for(int j = 0; j < m; j++) {
					tmp += arr[i][j];
				}
				
				min = Math.min(tmp,  min);
			}
			
			return;
		}
		
		for(int i = 0; i < temp.size(); i++) {
			if(!v[i]) {
				
				v[i] = true;
				sel[idx] = array[i];
				recursive(idx+1);
				v[i] = false;
			}
		}
	}

	//배열 회전
	private static void calc(int r, int c, int s) {
		
		//기준 좌표들
		int lr = r-s-1;
		int lc = c-s-1;
		int rr = r+s-1;
		int rc = c+s-1;
		
		//한 변씩 이동
		for(int cnt = 0; cnt < s; cnt++) {
			for(int i = lc+cnt; i < rc-cnt; i++) {
				marr[lr+cnt][i+1] = arr[lr+cnt][i];
			}
				
			for(int i = lr+cnt; i < rr-cnt; i++) {
				marr[i+1][rc-cnt] = arr[i][rc-cnt];
			}
				
			for(int i = rc-cnt; i > lc+cnt; i--) {
				marr[rr-cnt][i-1] = arr[rr-cnt][i];
			}
				
			for(int i = rr-cnt; i > lr+cnt; i--) {
				marr[i-1][lc+cnt] = arr[i][lc+cnt];
			}
		}
		
		//arr배열에 marr복사(다음 연산을 위해)
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				arr[i][j] = marr[i][j];
			}
		}
		
	}

	
}
