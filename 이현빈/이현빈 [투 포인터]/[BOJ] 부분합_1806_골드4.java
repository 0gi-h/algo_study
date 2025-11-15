import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S, start, end, sum, min;
    static int[] numArr;
    static final int INF = 123456789;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // 초기화
        start = 0;
        end = 0;
        sum = 0;
        min = INF;
        numArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numArr[i] = Integer.parseInt(st.nextToken());

        while (true)
        {
            if (sum >= S)
            {
                min = Math.min(min, end - start);
                sum -= numArr[start];
                start++;
            }
            else if (end == N)
                break;
            else
            {
                sum += numArr[end];
                end++;
            }
        }

        System.out.println(min == INF ? 0 : min);
    }
}
