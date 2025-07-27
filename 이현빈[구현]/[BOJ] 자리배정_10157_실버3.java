import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()); // 공연장 크기
        int find = Integer.parseInt(br.readLine()); // 내가 찾는 수

        if(find > N*M)
        {
            System.out.println(0);
            return;
        }

        int seatArr[][] = new int[M][N];

        int seatNumber = 1, startX = M - 1, startY = 0, turn = 1, x = 1, y = 1;

        while (true)
        {
            if (turn == 1)
            {
                while (true)
                {
                    if (seatArr[startX][startY] != 0)
                    {
                        // 누가 있음
                        startX++; // 배열의 인덱스
                        startY++; // 배열의 인덱스
                        x++; // 좌석 번호
                        y--; // 좌석 번호
                        break;
                    }
                    if (startX == 0)
                    {
                        // 끝
                        if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                            return;
                        seatNumber++;
                        startY++;
                        x++;
                        break;
                    }

                    if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                        return;
                    startX--;
                    seatNumber++;
                    y++;
                }
            }
            else if (turn == 2)
            {
                while (true)
                {
                    if (seatArr[startX][startY] != 0)
                    {
                        startX++;
                        startY--;
                        x--;
                        y--;
                        break;
                    }
                    if (startY == N - 1)
                    {
                        if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                            return;
                        seatNumber++;
                        startX++;
                        y--;
                        break;
                    }

                    if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                        return;
                    seatNumber++;
                    startY++;
                    x++;
                }
            }
            else if (turn == 3)
            {
                while (true)
                {
                    if (seatArr[startX][startY] != 0)
                    {
                        startY--;
                        startX--;
                        x--;
                        y++;
                        break;
                    }
                    if (startX == M - 1)
                    {
                        if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                            return;
                        seatNumber++;
                        startY--;
                        x--;
                        break;
                    }

                    if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                        return;
                    seatNumber++;
                    startX++;
                    y--;
                }
            }
            else if (turn == 4)
            {
                while (true)
                {
                    if (seatArr[startX][startY] != 0)
                    {
                        startY++;
                        startX--;
                        x++;
                        y++;
                        turn = 0;
                        break;
                    }
                    if (checkSeat(seatArr, startX, startY, seatNumber, find, x, y))
                        return;
                    seatNumber++;
                    startY--;
                    x--;
                }
            }

            turn++;
        }
    }

    public static boolean checkSeat(int seatArr[][], int startX, int startY, int seatNumber, int k, int x, int y)
    {
        seatArr[startX][startY] = seatNumber;
        if(seatNumber == k)
        {
            System.out.println(x + " " + y);
            return true;
        }
        else
        {
            return false;
        }
    }
}
