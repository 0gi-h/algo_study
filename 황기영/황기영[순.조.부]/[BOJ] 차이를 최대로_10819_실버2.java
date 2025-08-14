import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr;
	static int[] sel;
	static boolean[] v;
	static int sum = 0;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		sel = new int[n];
		v = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		recursive(0);
		
		System.out.println(result);
	}

	private static void recursive(int idx) {
		if(idx == arr.length) {
			sum = 0;
			
			for(int i = 0; i < arr.length - 1; i++) {
				sum += Math.abs(sel[i] - sel[i+1]);
			}
			
			result = Math.max(result, sum);
			
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(v[i] == false) {
				v[i] = true;
				sel[idx] = arr[i];
				recursive(idx+1);
				v[i] = false;
			}
		}

	}

}
