import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

public class Main {
	
	static int result = Integer.MIN_VALUE;
	static int cnt;
	static ArrayList<Integer> num;
	static ArrayList<String> op;
 	
	public static int math(int a, String b, int c) {
		int result = 0;
		
		if(b.equals("+")) {
			result = a + c;
		}
		else if(b.equals("-")) {
			result = a - c;
		}
		else if(b.equals("*")) {
			result = a * c;
		}
		
		return result;
	}
	
	public static void dfs(int temp, int idx) {
		if(idx >= cnt) {
			result = Math.max(temp, result);
			return;
		}
		
		int tmp1 = math(temp, op.get(idx), num.get(idx + 1));
		dfs(tmp1, idx+1);
		
		if(idx+1 < cnt) {
			int tmp2 = math(num.get(idx+1), op.get(idx+1), num.get(idx+2));
			dfs(math(temp, op.get(idx), tmp2), idx+2);
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		
		num = new ArrayList<>();
		op = new ArrayList<>();
		cnt = N/2;
		String s = sc.nextLine();
		String[] sArr = s.split("");
		
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0) {
				num.add(Integer.parseInt(sArr[i]));
			}
			else {
				op.add(sArr[i]);
			}
		}
		
		dfs(num.get(0), 0);
		
		System.out.println(result);
	}
}
