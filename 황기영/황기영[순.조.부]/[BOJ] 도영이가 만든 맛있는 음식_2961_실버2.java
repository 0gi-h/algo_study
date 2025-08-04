import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] ingredients;
	static boolean[] sel;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ingredients = new int[N][2];
		sel = new boolean[ingredients.length];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}
		
		recursive(0);
		
		System.out.println(result);
	}
	
	static void recursive(int idx) {
		if(idx == ingredients.length) {
			int S = 1;
			int B = 0;
			for(int i = 0; i < sel.length; i++) {
				if(sel[i]) {
					S = S * ingredients[i][0];
					B = B + ingredients[i][1];
				}
			}
			
			if(S != 1 && B != 0) {
				result = Math.abs(S-B) < result ? Math.abs(S-B) : result;
			}
			
			return;
		}
		
		sel[idx] = true;
		recursive(idx+1);
		sel[idx] = false;
		recursive(idx+1);
		
	}

}
