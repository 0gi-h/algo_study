import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[n];
        // 부분 수열의 길이의 초기 값 설정을 위해 전부 1로 채웠습니다. 만약 불편하시다면 후에 n - max - 1을 진행하면 됩니다.
        Arrays.fill(dp, 1);
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 1;
        for(int i = 1; i < n; i++){
            for(int j = i-1; j >= 0; j--){
                if(arr[i] < arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        System.out.println(n - max);
    }
}
