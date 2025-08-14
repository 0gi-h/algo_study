import java.io.*;
import java.util.*;

public class Main
{
    static int max = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for(int i = 0; i < N ; i++)
            arr[i] = Integer.parseInt(st.nextToken()); // 원본 배열

        boolean[] check = new boolean[N]; // 방문 배열

        int[] sel = new int[N]; // 선택 배열

        recursive(arr,sel , check, 0); // 재귀

        System.out.println(max);
    }

    private static void recursive(int[] arr, int[] sel, boolean[] check, int idx)
    {
        // b
        if(idx == arr.length)
        {
            int sum = 0;

            for(int i = 0; i < sel.length; i++) // 수열의 합 계산
            {
                if(i == sel.length - 1) // 여기까지~
                    break;

                sum += Math.abs(sel[i] - sel[i+1]);
            }

            max = Math.max(max, sum); // max 갱신

            return;
        }

        // i
        for(int i = 0; i < arr.length; i++)
        {
            if(!check[i])
            {
                check[i] = true;
                sel[idx] = arr[i]; // 선택
                recursive(arr, sel, check, idx + 1);
                check[i] = false;
            }
        }
    }
}
