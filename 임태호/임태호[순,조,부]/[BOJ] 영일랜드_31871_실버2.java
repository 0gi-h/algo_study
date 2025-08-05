import java.io.*;
import java.util.*;
public class Main{
	static boolean[] v;
	static int[] sel;
	static int[][] arr;
	static int n;
	static int max = -1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+1];
		sel = new int[n];
		v = new boolean[n+1];
		StringTokenizer st;
		for(int i = 0; i<  m; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr[start][end] = Math.max(arr[start][end], cost);
		}
		permutation(0);
		System.out.println(max);
	}
	static void permutation(int idx){
		if(idx == n){
			counting();
			return;
		}

		for(int i = 1; i <= n; i++){
			if(!v[i]){
				v[i] = true;
				sel[idx] = i;
				permutation(idx+1);
				v[i] = false;
			}
		}
	}
	static void counting(){
		int sum = 0;
		int start = arr[0][sel[0]];
		int end = arr[sel[n-1]][0];
		if(start == 0 || end == 0) return;
		sum += start + end;
		for(int i = 0; i< n-1; i++){
			int now = arr[sel[i]][sel[i+1]];
			if(now == 0) return;
			sum += now;
		}
		max = Math.max(sum, max);
	}

}
