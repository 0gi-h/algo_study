import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    static int n;
    static int[] sel;
    static boolean[] v;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        v = new boolean[n];
        sel = new int[n];
        max = 0;
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        permutation(0);
        System.out.println(max);
    }
    public static void permutation(int index){
        if(index == n){
            calculate();
            return;
        }
        for(int i = 0; i < n ; i++){
            if(v[i]) continue;
            v[i] = true;
            sel[index] = arr[i];
            permutation(index+1);
            v[i] = false;
        }
    }
    public static void calculate(){
        int sum = 0;
        for(int i = 0; i < n-1; i++){
            sum += Math.abs(sel[i] - sel[i+1]);
        }
        max = Math.max(sum,max);
    }
}
