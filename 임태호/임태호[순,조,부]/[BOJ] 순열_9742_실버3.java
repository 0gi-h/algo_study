import java.util.*;
import java.io.*;
public class Main {
	static StringBuilder sb;
	static String problem;
	static int count;
	static int num;
	static char[] sel;
	static boolean[] v;
	static boolean check;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		String s = "";
		while( (s = br.readLine())  != null){
			st = new StringTokenizer(s);
			problem = st.nextToken();
			sel = new char[problem.length()];
			v = new boolean[problem.length()];
			sb.append(problem).append(" ");
			count = Integer.parseInt(st.nextToken());
			sb.append(count).append(" = ");
			if(over(problem.length(), count)){
				sb.append("No permutation").append("\n");
				continue;
			}
			num = 1;
			check = false;
			permutation(0);

		}
		System.out.println(sb);

	}

	private static boolean over(int length, int count) {
		int fact = 1;
		for (int i = 1; i <= length; i++) {
			fact *= i;
			if (fact >= count) return false; // count 이하면 충분함
		}
		return true;
	}

	static void permutation(int cnt){
		if(cnt == problem.length()){
			if(num == count){
				for(int i = 0; i < sel.length; i++){
					sb.append(sel[i]);
				}
				sb.append("\n");
				check = true;
			}
			num++;
			return;
		}
		if(check) return;
		for(int i = 0; i < problem.length(); i++){
			if(!v[i]){
				v[i] = true;
				sel[cnt] = problem.charAt(i);
				permutation(cnt+1);
				v[i] = false;
			}
		}
	}

}
