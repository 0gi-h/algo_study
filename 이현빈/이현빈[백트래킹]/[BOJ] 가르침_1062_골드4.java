import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, cnt;
    static String[] wordArr;
    static boolean[] learnArr;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cnt = 0;

        wordArr = new String[N];
        for (int i = 0; i < N; i++)
            wordArr[i] = br.readLine();

        learnArr = new boolean[26];
        learnArr['a' - 'a'] = true;
        learnArr['n' - 'a'] = true;
        learnArr['t' - 'a'] = true;
        learnArr['i' - 'a'] = true;
        learnArr['c' - 'a'] = true;

        recursive(0, 0);

        System.out.println(cnt);
    }

    private static void recursive(int n, int k)
    {
        // b
        if (k == K - 5)
        {
            int compareCnt = 0;

            for (int i = 0; i < N; i++)
            {
                boolean isCan = true;

                for (int j = 4; j < wordArr[i].length() - 4; j++) // 앞에 4자리, 뒤에 4자리는 안봐도됨
                {
                    if (!learnArr[wordArr[i].charAt(j) - 'a']) // 못읽음
                    {
                        isCan = false;
                        break;
                    }
                }

                if (isCan) compareCnt++;
            }

            cnt = Math.max(cnt, compareCnt);
            return;
        }

        // i
        for (int i = n; i < 26; i++)
        {
            if (!learnArr[i])
            {
                learnArr[i] = true;
                recursive(i + 1, k + 1);
                learnArr[i] = false;
            }
        }
    }
}
