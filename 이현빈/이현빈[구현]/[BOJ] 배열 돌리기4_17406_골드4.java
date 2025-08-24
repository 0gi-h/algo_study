import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K, Min;
    static int[][] mapArr;

    static int[] dx = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dy = {1, 0, -1, 0};

    static List<int[]>[] permutationList;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Min = Integer.MAX_VALUE;

        mapArr = new int[N][M];

        permutationList = new List[K];

        for(int i = 0; i < K; i++)
            permutationList[i] = new ArrayList<>();

        // 입력
        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                mapArr[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < K; i++)
        {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            permutationList[i].add(new int[]{r, c, s});
        }

        // 순열 뽑고 시뮬 돌리고 최솟값
        boolean[] check = new boolean[K];
        List<int[]>[] sel = new List[K];

        for(int i = 0; i < K; i++)
            sel[i] = new ArrayList<>();

        Permutation(sel , check, 0);

        System.out.println(Min);
    }

    private static void Permutation(List<int[]>[] sel, boolean[] check, int idx)
    {
        // b
        if (idx == K)
        {
            int[][] cur = new int[N][M];

            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < M; j++)
                    cur[i][j] = mapArr[i][j];
            }

            for (int i = 0; i < K; i++)
                Simulator(cur, sel[i].get(0)[0], sel[i].get(0)[1], sel[i].get(0)[2]); // 0번째에 r, 1번째에 c, 2번째에 s가 들어있음

            // MIN 계산하기~
            for (int[] row : cur)
            {
                int s = 0;

                for (int v : row)
                    s += v;

                Min = Math.min(Min,s);
            }

            return;
        }

        // i
        for(int i = 0; i < permutationList.length; i++)
        {
            if(!check[i])
            {
                check[i] = true;
                sel[idx] = permutationList[i];
                Permutation(sel, check, idx + 1);
                check[i] = false;
            }
        }
    }

    private static void Simulator(int[][] copyMapArr, int r, int c, int s)
    {
        // 진짜 좌표
        int sr = (r - s) - 1;
        int sc = (c - s) - 1;
        int er = (r + s) - 1;
        int ec = (c + s) - 1;

        int count = (er - sr) + 1; // 회전해야 하는 영역의 배열 크기
        int[][] copyArr = new int[count][count];

        // 원본 가져오기
        for (int i = 0; i < count; i++)
        {
            for (int j = 0; j < count; j++)
                copyArr[i][j] = copyMapArr[sr + i][sc + j];
        }

        /* code form GPT */

        // 레이어별로 한 칸 회전 (copyArr -> mapArr)
        int top = 0, left = 0, bottom = count - 1, right = count - 1;

        while (top < bottom && left < right)
        {
            // 상: → (오른쪽으로 한 칸)  dst(top, j) = src(top, j-1)
            for (int j = left + 1; j <= right; j++)
                copyMapArr[sr + top][sc + j] = copyArr[top][j - 1];

            // 우: ↓ (아래로 한 칸)      dst(i, right) = src(i-1, right)
            for (int i = top + 1; i <= bottom; i++)
                copyMapArr[sr + i][sc + right] = copyArr[i - 1][right];

            // 하: ← (왼쪽으로 한 칸)    dst(bottom, j) = src(bottom, j+1)
            for (int j = right - 1; j >= left; j--)
                copyMapArr[sr + bottom][sc + j] = copyArr[bottom][j + 1];

            // 좌: ↑ (위로 한 칸)        dst(i, left) = src(i+1, left)
            for (int i = bottom - 1; i >= top; i--)
                copyMapArr[sr + i][sc + left] = copyArr[i + 1][left];

            top++; left++; bottom--; right--;
        }

        /* end */
    }
}
