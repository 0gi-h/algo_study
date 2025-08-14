import java.util.*;
import java.io.*;
class Main {
    static int result;
    static int m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = 0;
        powerset(0, 0);
        System.out.println(m == 0 ? result -1 : result);
    }
    public static void powerset(int index, int sum){
        if(index == arr.length){
            if(sum == m) result++;
            return;
        }
        powerset(index+1, sum+arr[index]);
        powerset(index+1, sum);
    }
}
