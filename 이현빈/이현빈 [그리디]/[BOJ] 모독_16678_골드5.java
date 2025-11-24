import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] honorArr;
    static long hacker;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());
        honorArr = new int[N];

        for(int i = 0; i < N; i++)
            honorArr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(honorArr);

        hacker = 0;

        if(honorArr[0] != 1)
        {
            hacker += honorArr[0] - 1;
            honorArr[0] = 1;
        }

        for(int i = 1; i < N; i++)
        {
            if(honorArr[i] > honorArr[i-1] + 1)
            {
                hacker += honorArr[i] - (honorArr[i-1] + 1);
                honorArr[i] = honorArr[i-1] + 1;
            }
        }

        System.out.println(hacker);
    }
}
