package Study;
import java.io.*;
import java.util.*;
public class Solider {
    static ArrayList<Integer> dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        dp = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp.add(arr[0]);

        for(int i = 1; i < n; i++){
            if(arr[i] < dp.get(dp.size()-1)){
                dp.add(arr[i]);
            }else if(arr[i] > dp.get(dp.size()-1)){
                // 배열의 값 중 처음으로 작아지는 값의 위치입니다.
                int index = binarySearch(arr[i]);
                dp.remove(index);
                dp.add(index, arr[i]);
            }
        }

        System.out.println(n - dp.size());


    }
    static int binarySearch(int target){
        int low = 0;
        int high = dp.size()-1;

        while(low <= high){
            int mid = low + (high - low)/2;

            if(dp.get(mid) > target){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }

        return low;

    }
}
