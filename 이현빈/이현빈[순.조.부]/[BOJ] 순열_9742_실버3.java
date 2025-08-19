import java.io.*;
import java.util.*;

public class Permutation_250802
{
    static int check = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) // 더 이상 입력이 없을때까지
        {
            StringTokenizer st = new StringTokenizer(line);

            String str = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            Character arr[] = new Character[str.length()];

            for(int i = 0; i < str.length(); i++)
                arr[i] = str.charAt(i);

            Character sel[] = new Character[arr.length];
            boolean visited[] = new boolean[arr.length];

            // 초기화
            check = 0;

            // 재귀를 돌것인가 말것인가
            int sum = 1;

            for(int i = arr.length; i  > 0; i--)
                sum *= i;

            if(sum < number)
            {
                // 얜 못뽑음
                sb.append(str).append(" ").append(number).append(" = ").append("No permutation").append("\n");
            }
            else
            {
                recursive(arr, sel, visited, str, 0, number);
            }
        }

        System.out.println(sb);
    }

    private static void recursive(Character arr[], Character[] sel, boolean[] visited, String str, int idx, int checkNum)
    {
        // b
        if(idx == arr.length)
        {
            check++;

            if(check == checkNum) // 내가 원하던 위치
            {
                StringBuilder sbTemp = new StringBuilder();

                for(int i = 0; i < sel.length; i++)
                    sbTemp.append(sel[i]);

                sb.append(str).append(" ").append(checkNum).append(" = ").append(sbTemp).append("\n");
            }
            return;
        }

        // i
        for(int i = 0; i < arr.length; i++)
        {
            // 방문 체크
            if(!visited[i])
            {
                sel[idx] = arr[i];
                visited[i] = true;
                recursive(arr, sel, visited, str, idx + 1, checkNum);
                visited[i] = false;
            }
        }
    }
}

// https://www.acmicpc.net/problem/9742
