import java.io.*;
import java.util.*;
public class Main{
	static long[] sour;
	static long[] bitter;
	static int[] sel;
	static int n;
	static long min = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sour = new long[n];
		bitter = new long[n];
		sel = new int[n];
		Arrays.fill(sel , -1);
		StringTokenizer st;
		for(int i =0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			sour[i] = Long.parseLong(st.nextToken());
			bitter[i] = Long.parseLong(st.nextToken());
		}
		combination(0);
		System.out.println(min);
	}
	public static void combination(int idx){
		if(idx == n){
			check();
			return;
		}
		sel[idx] = idx;
		combination(idx+1);
		sel[idx] = -1;
		combination(idx+1);
	}
	public static void check(){
		long sourPow = 1;
		long bitterSum = 0;
		for(int i = 0; i < sel.length; i++){
			if(sel[i] == -1) continue;
			sourPow *= sour[sel[i]];
			bitterSum += bitter[sel[i]];
		}
		if(sourPow == 1) return;
		min = Math.min(min, Math.abs(sourPow-bitterSum));
	}
}
