import java.io.*;
import java.util.*;

public class GoodFoodByDooYoung_250802
{
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int tasteArr[][] = new int[N][2];
        boolean sel[] = new boolean[N];

        for (int i = 0; i < N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sour = Integer.parseInt(st.nextToken());
            int bad = Integer.parseInt(st.nextToken()); // 쓴맛... 이 영어로 뭐죠?

            tasteArr[i][0] = sour;
            tasteArr[i][1] = bad;
        }

        recursive(tasteArr, sel, 0);

        System.out.println(min);
    }

    private static void recursive(int[][] tasteArr, boolean[] sel, int idx)
    {
        // b
        if (idx == tasteArr.length)
        {
            int sour = 1;
            int bad = 0;

            for (int i = 0; i < sel.length; i++)
            {
                if (sel[i])
                {
                    sour *= tasteArr[i][0];
                    bad += tasteArr[i][1];

                    int dif = Math.max(sour, bad) - Math.min(sour, bad);

                    if (dif < min)
                        min = dif;
                }
            }
            return;
        }

        // i
        sel[idx] = true; // 선택
        recursive(tasteArr, sel, idx + 1);

        sel[idx] = false; // No 선택
        recursive(tasteArr, sel, idx + 1);
    }
}
