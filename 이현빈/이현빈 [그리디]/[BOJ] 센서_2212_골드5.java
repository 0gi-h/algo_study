import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, min;
    static int[] sensorArr;
    static int[] distanceDiffArr;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        sensorArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            sensorArr[i] = Integer.parseInt(st.nextToken());

        distanceDiffArr = new int[N - 1];

        // 원본 정렬
        Arrays.sort(sensorArr);

        // 거리차이
        for(int i = 0; i < N - 1; i++)
            distanceDiffArr[i] = sensorArr[i+1] - sensorArr[i];

        // 거리차이 정렬
        Arrays.sort(distanceDiffArr);

        // K개 빼고
        for(int i = 0; i < distanceDiffArr.length - (K-1); i++)
            min += distanceDiffArr[i];

        System.out.println(min);
    }
}
