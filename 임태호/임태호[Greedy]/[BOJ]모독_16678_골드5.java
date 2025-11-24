import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);
        long hacker = 0;
        int need = 1;
        for(int i = 0 ; i < N; i++){
            if(need < nums[i]){
                hacker += nums[i] - need;
                need++;
            }else if(need == nums[i]){
                need++;
            }
        }
        System.out.println(hacker);
    }
}
