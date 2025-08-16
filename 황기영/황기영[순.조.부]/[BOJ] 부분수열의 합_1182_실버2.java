import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int s;
	static int[] arr;
	static boolean[] v;
	static int sum  = 0;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		arr = new int[n];
		v = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		recursive(0);
		
		
		System.out.println(cnt);
		
	}

	private static void recursive(int idx) {
		

		
		if(idx == arr.length) {
			return;
		}
		
		v[idx] = true;
		sum += arr[idx];
		
		if(sum == s) {
			cnt++;
		}
		
		recursive(idx + 1);
		v[idx] = false;
		sum -= arr[idx];
		recursive(idx+1);
	}

}
