import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];

        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        if(K >= N){
            System.out.println(0);
            return;
        }

        Arrays.sort(nums);
        int[] dif = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dif[i] = nums[i + 1] - nums[i];
        }
        Arrays.sort(dif);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += dif[i];
        }

        System.out.println(sum);
    }
}
