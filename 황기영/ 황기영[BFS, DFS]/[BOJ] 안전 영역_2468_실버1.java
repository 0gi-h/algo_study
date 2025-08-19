import java.util.Scanner;

public class Main {
	public static int[] dr = {1, 0, -1, 0};
	public static int[] dc = {0, -1, 0, 1};
	static int[][] arr;
	static boolean[][] arr2;
	static int N;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N][N];
		int max = 0;
		int result = 1;

		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = sc. nextInt();
				max = max < arr[i][j] ? arr[i][j] : max;
			}
		}
		
		for(int i = 1; i <= max; i++) {
			arr2 = new boolean[N][N];
			int cnt = 0;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(arr[j][k] > i && !arr2[j][k]) {
						cnt++;
						dfs(j, k, i);
					}
				}
			}
			
			result = result < cnt ? cnt : result;
		}
		System.out.println(result);
	}

	static void dfs(int j, int k, int h) {
		arr2[j][k] = true;
		for(int i = 0; i < 4; i++) {
			int nr = j + dr[i];
			int nc = k + dc[i];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && !arr2[nr][nc] && arr[nr][nc] > h) {
				dfs(nr, nc, h);
			
			}

		}
		
	}
}
