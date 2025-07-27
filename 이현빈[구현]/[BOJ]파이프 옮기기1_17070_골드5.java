import java.io.*;
import java.util.*;

public class MovePipe_250726
{
    static int count;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int homeArr[][] = new int[N][N];

				// 값 입력
        for (int i = 0; i < N; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                homeArr[i][j] = Integer.parseInt(st.nextToken());
        }


        // 가로 호출
        MovePipe(homeArr, 0, 0, 1, N); // 가로 0,1부터

        System.out.println(count);
    }

    static void MovePipe(int homeArr[][], int type, int x, int y, int N)
    {
        if(x == N - 1 && y == N - 1)
        {
            count++;
            return;
        }

        // 가로
        if(type == 0)
        {
            if(y + 1 < N && homeArr[x][y+1] == 0) // 가로
                MovePipe(homeArr, 0, x,y + 1, N);

            if(x + 1 < N && y + 1 < N && homeArr[x][y+1] == 0 && homeArr[x+1][y] == 0 && homeArr[x+1][y+1] == 0)  // 대각선
                MovePipe(homeArr, 1, x+1, y+1, N);
        }

        // 대각선
        if(type == 1)
        {
            if(y + 1 < N && homeArr[x][y+1] == 0) // 가로
                MovePipe(homeArr, 0, x,y + 1, N);

            if(x + 1 < N && y + 1 < N && homeArr[x][y+1] == 0 && homeArr[x+1][y] == 0 && homeArr[x+1][y+1] == 0)  // 대각선
                MovePipe(homeArr, 1, x + 1, y + 1, N);

            if(x + 1 < N && homeArr[x+1][y] == 0) // 세로
                MovePipe(homeArr, 2, x + 1, y, N);
        }

        // 세로
        if(type == 2)
        {
            if(x + 1 < N && homeArr[x+1][y] == 0) // 세로
                MovePipe(homeArr, 2, x + 1, y, N);

            if(x + 1 < N && y + 1 < N && homeArr[x][y+1] == 0 && homeArr[x+1][y] == 0 && homeArr[x+1][y+1] == 0)  // 대각선
                MovePipe(homeArr, 1, x + 1, y + 1, N);
        }
    }
}

