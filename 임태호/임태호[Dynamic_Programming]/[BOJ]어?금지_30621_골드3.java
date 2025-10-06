import java.io.*;
import java.util.*;
public class Main {
    static long[] t;
    static long[] b;
    static long[] c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        t =  new long[N+1];
        for (int i = 1; i <= N; i++) {
            t[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        b =  new long[N+1];
        for (int i = 1; i <= N; i++) {
            b[i] = Long.parseLong(st.nextToken());
        }
        st =  new StringTokenizer(br.readLine());
        c  =  new long[N+1];
        for (int i = 1; i <= N; i++) {
            c[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[N+1];

        for(int i = 1; i < dp.length; i++){
            int index = binarySearch(1, i-1, t[i] - b[i]-1);
            dp[i] = dp[i-1];
            if(index != t[i] - b[i]-1){
                if(index - 1 >= 0){
                    dp[i] = Math.max(dp[i] , dp[index-1] + c[i]);
                }
            }else{
                dp[i] = Math.max(dp[i] , dp[index] + c[i]);
            }
        }
        System.out.println(dp[dp.length-1]);
    }
    public static int binarySearch(int start, int end, long target){
        if(start > end) return start;
        int mid = start + (end-start) / 2;

        if(t[mid] > target) return binarySearch(start, mid-1, target);
        else return binarySearch(mid+1, end, target);
    }
}
