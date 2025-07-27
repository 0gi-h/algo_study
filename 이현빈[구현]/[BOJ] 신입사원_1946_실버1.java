import java.io.*;
import java.util.*;

public class NewEmployee_250726
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();

        for (int test_case = 0; test_case < T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());

            Map<Integer, Integer> rankMap = new HashMap<>();

            // 값 입력
            for (int i = 0; i < N; i++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                rankMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // Key 기준으로 오름차순 정렬 (검색)
            List<Integer> sortList = new ArrayList<>(rankMap.keySet());
            Collections.sort(sortList);

            int max = rankMap.get(1), count = 1; // 1명 뽑은채로 시작

            for (int i = 2; i <= N; i++)
            {
                if(rankMap.get(i) < max)
                {
                    max = rankMap.get(i); // 갱신
                    count++; // 1명 뽑음
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
