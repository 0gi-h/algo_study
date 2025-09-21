import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2447 {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				recursive(i, j, n);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void recursive(int i, int j, int n) {
		if((i/n) % 3 == 1 && (j/n) % 3 == 1) {
			sb.append(" ");
		}
		else if(n / 3 == 0) {
			sb.append("*");
		}
		else {
			recursive(i, j, n/3);
		}
	}

}
