import java.io.*;
import java.util.*;

public class Main
{
    static int totalCount = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int arr[] = new int[N];

        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        boolean[] sel = new boolean[N]; // 방문 배열

        recursive(arr, sel, 0, S); // 재귀

        System.out.println(totalCount);
    }

    private static void recursive(int[] arr, boolean[] sel, int idx, int S)
    {
        // b
        if(idx == arr.length)
        {
            if(checkSelAllFalse(sel))
                return;

            int count = 0;

            for(int i = 0; i < sel.length; i++) // 조건 확인
            {
                if(sel[i])
                    count += arr[i];
            }

            if(count == S)
                totalCount++; // 조건과 일치함

            return;
        }

        // i
        sel[idx] = true;
        recursive(arr, sel, idx + 1, S);

        sel[idx] = false;
        recursive(arr, sel, idx + 1, S);
    }

    private static boolean checkSelAllFalse(boolean[] sel)
    {
        boolean isTrue = true;

        for(int i = 0; i < sel.length; i++)
        {
            if (sel[i])
            {
                isTrue = false;
                break;
            }
        }
        return isTrue;
    }
}
