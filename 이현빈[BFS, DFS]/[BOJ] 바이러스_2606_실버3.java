import java.io.*;
import java.util.*;

public class Virus_250723
{
    static ArrayList<Integer>[] graph; // 컴퓨터
    static boolean[] visited; // 방문 했는지 체크 할 배열
    static int count = 0; // 총 몇대 방문 했는지 갯수

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1]; // 컴퓨터 생성
        
        // 컴퓨터 정보 입력
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

				// 방문 배열 생성
        visited = new boolean[N + 1];

        visited[1] = true; // 1번은 감염됨
        dfs(1); // 1번 안에 있는 놈들부터 방문해 !

        System.out.println(count);
    }

    static void dfs(int node)
    {
        for (int next : graph[node])
        {
            if (!visited[next])
            {
                visited[next] = true;
                count++;
                dfs(next);
            }
        }
    }
}
