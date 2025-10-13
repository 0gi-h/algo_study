import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int target = Integer.parseInt(br.readLine());
		int[][] dp = new int[11][10];
		Arrays.fill(dp[1], 1);
		// 10^3
		if(target <= 9) {
			System.out.print(target);
		}else{
			int find = 9;
			int digit = -1;
			int num = -1;
			boolean check = false;
			for(int i = 2; i < 11; i++){
				if(check) break;
				for(int j = 0; j < 10; j++){
					int sum = 0;
					for(int k = j-1 ; k>=0; k-- ){
						sum += dp[i-1][k];
					}
					dp[i][j] = sum;
					find+= sum;
					if(find >= target){
						check = true;
						find-= sum;
						digit = i;
						num = j;
						break;
					}
				}
			}
			long result = num;
			digit--;
			int index = 0;
 			while(digit > 0){
 				find += dp[digit][index];
				if(find >= target){
					find-=dp[digit][index];
					result = result*10 + index;
					index = 0;
					digit--;
					continue;
				}
				index++;
			}
			System.out.println(result);
		}
	}
}
