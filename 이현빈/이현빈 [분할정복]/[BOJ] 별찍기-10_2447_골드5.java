import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static char[][] starArr;

    public static void main(String[] args) throws IOException
    {
        N = Integer.parseInt(br.readLine());
        starArr = new char[N][N];

        division(0, 0, N, false);

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                sb.append(starArr[i][j] == '*' ? '*' : ' ');
            sb.append('\n');
        }

        System.out.println(sb);
    }

    private static void division(int x, int y, int size, boolean isPass)
    {
        if (isPass)
        {
            for (int i = x; i < x + size; i++)
            {
                for (int j = y; j < y + size; j++)
                    starArr[i][j] = ' ';
            }
            return;
        }

        if (size == 1)
        {
            starArr[x][y] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;

        for (int i = x; i < x + size; i += newSize)
        {
            for (int j = y; j < y + size; j += newSize)
            {
                count++;
                division(i, j, newSize, count == 5);
            }
        }
    }
}
