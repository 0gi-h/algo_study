import java.io.*;
import java.util.*;

public class Main 
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, myX, myY, chX, chY;
    static int[][] mapArr;

    static List<int[]> myHousePosList;
    static List<int[]> chickenPosList;

    static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapArr = new int[N][N];
        myHousePosList = new ArrayList<>();
        chickenPosList = new ArrayList<>();

        for(int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
            {
                mapArr[i][j] = Integer.parseInt(st.nextToken());
                if(mapArr[i][j] == 2)
                    chickenPosList.add(new int[]{i, j});
                else if(mapArr[i][j] == 1)
                    myHousePosList.add(new int[]{i, j});
            }
        }

        // M 개 만큼의 치킨집 좌표를 뽑아야함.
        int[] arr = new int[chickenPosList.size()];
        for(int i = 0; i < arr.length; i++)
            arr[i] = i;

        int[] sel = new int[M];
        ChickenCombination(arr, sel, 0, 0);
        System.out.println(MIN);
    }

    private static void ChickenCombination(int[] arr, int[] sel, int idx, int k)
    {
        // b
        if(k == M)
        {
            int roadSum = 0;

            for (int i = 0; i < myHousePosList.size(); i++)
            {
                myX = myHousePosList.get(i)[0];
                myY = myHousePosList.get(i)[1];
                int road = Integer.MAX_VALUE;

                for (int j = 0; j < sel.length; j++)
                {
                    chX = chickenPosList.get(sel[j])[0];
                    chY = chickenPosList.get(sel[j])[1];

                    int temp = Math.abs(chX - myX) + Math.abs(chY - myY);

                    if(temp < road)
                        road = temp;
                }

                roadSum += road;

                if(roadSum > MIN)
                    return;
            }

            MIN = Math.min(MIN, roadSum);

            return;
        }

        if(idx == chickenPosList.size())
            return;

        // i
        sel[k] = arr[idx];
        ChickenCombination(arr, sel, idx + 1, k + 1);
        ChickenCombination(arr, sel, idx + 1, k);
    }
}
