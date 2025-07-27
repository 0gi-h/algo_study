import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());

        char bombArr[][] = new char[R][C]; // 폭탄 배열

        // 폭탄 위치 입력
        for (int i = 0; i < R; i++)
        {
            String line = br.readLine();

            for (int j = 0; j < line.length(); j++)
                bombArr[i][j] = line.charAt(j);
        }

        // 1초 대기
        N--;

        // 여기부턴 반복의 시작
        // 1초 동안 폭탄이 설치 되어 있지 않은 모든 칸에 폭탄을 설치한다.
        // 1초가 지난 후엔 가운데에 있는 폭탄이 폭발해 가운데 칸과 인접한 네 칸이 빈 칸이 된다.
        boolean isExplodeTurn = false; // 폭발할 차례인가
        for (int time = N; time > 0; time--)
        {
            if(!isExplodeTurn) // 설치 할 차례
            {
                InsertBomb(bombArr, R, C);
                isExplodeTurn = true;
            }
            else // 폭발할 차례
            {
                ExplodeBomb(bombArr, R, C);
                isExplodeTurn = false;
            }
        }

        DrawArr(bombArr, R, C);
    }

    /// 폭탄 폭발시키는 함수
    private static void ExplodeBomb(char bombArr[][], int R, int C)
    {
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                if (bombArr[i][j] == 'x') // 폭탄 발견
                {
                    bombArr[i][j] = '.'; // 내 자리 폭발

                    if (i - 1 >= 0 && bombArr[i - 1][j] != 'x') // 상
                        bombArr[i - 1][j] = '.';

                    if (i + 1 < R && bombArr[i + 1][j] != 'x') // 하
                        bombArr[i + 1][j] = '.';

                    if (j - 1 >= 0 &&  bombArr[i][j - 1] != 'x') // 좌
                        bombArr[i][j - 1] = '.';

                    if (j + 1 < C && bombArr[i][j + 1] != 'x') // 우
                        bombArr[i][j + 1] = '.';
                }
            }
        }
    }

    /// 폭탄 설치해주는 함수
    private static void InsertBomb(char bombArr[][], int R, int C)
    {
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                // .은 모두 o로,  o는 모두 x로
                if(bombArr[i][j] == '.')
                    bombArr[i][j] = 'O'; // 폭탄 심기
                else if(bombArr[i][j] == 'O')
                    bombArr[i][j] = 'x'; // 얘는 1초 뒤에 터져야 되는 폭탄
            }
        }
    }

    /// 폭탄을 그려주는 함수
    static void DrawArr(char bombArr[][], int R, int C)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                if(bombArr[i][j] == 'x') // 터지지 않은 폭탄은 O로 바꿔서 출력
                    bombArr[i][j] = 'O';

                sb.append(bombArr[i][j]);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
