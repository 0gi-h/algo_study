import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        long[] arr = new long[N+1];
        st=new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        for(int i = 1; i <= N; i++){
            arr[i] = arr[i-1] + arr[i];
        }

        if(arr[N] < S){
            System.out.println(0);
        }else{
            int min = 100_000;

            int start = 0;
            int end = 0;
            while(start <= end && end <= N){
                long sum = arr[end] - arr[start];
                if(sum < S){
                    end++;
                }else if(sum >= S){
                    min = Math.min(min, end-start);
                    start++;
                }

            }
            System.out.println(min);
        }
    }
}
