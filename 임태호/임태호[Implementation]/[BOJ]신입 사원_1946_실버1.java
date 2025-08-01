import java.io.*;
import java.util.*;
public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n+1];
            for(int j = 0; j < n ; j++){
                st = new StringTokenizer(br.readLine());
                int paper = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                arr[paper] = interview;
            }
            int count = 0;
            int min = Integer.MAX_VALUE;
            for(int j = 1; j <= n; j++){
                if(arr[j] < min){
                    count++;
                    min = arr[j];
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
