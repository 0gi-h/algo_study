import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());

        // 걍 다 뽑아버려
        for (int i = 0; i <= 9; i++)
            PickNumber(i);

        Collections.sort(list);

        // System.out.println(list.size()); <- 이게 1023개
        // 1022번째 감소하는 수가 9876543210 임

        System.out.println(N >= 1023 ? -1 : list.get(N));
    }

    static void PickNumber(long num)
    {
        list.add(num);

        for (long i = 0; i < num % 10; i++)
            PickNumber(num * 10 + i);
    }
}
