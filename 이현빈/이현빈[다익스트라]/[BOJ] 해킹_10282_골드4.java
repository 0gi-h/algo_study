import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;

    static class Edge
    {
        int e, w;

        public Edge(int e, int w)
        {
            this.e = e;
            this.w = w;
        }
    }

    static List<Edge>[] edgeArr;
    static int N, D, C, cnt, cost;
    static int[] costArr;
    static final int INF = 123456789;

    public static void main(String[] args) throws IOException
    {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++)
        {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            // 초기화 다 여기서
            cnt = 0;
            cost = 0;
            costArr = new int[N+1];
            Arrays.fill(costArr, INF);

            // 간선
            edgeArr = new List[N+1];
            for (int i = 1; i <= N; i++)
                edgeArr[i] = new ArrayList<>();
            for (int i = 0; i < D; i++)
            {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                edgeArr[b].add(new Edge(a, s));
            }

            dijkstra();

            sb.append(cnt).append(" ").append(cost).append("\n");
        }

        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }

    private static void dijkstra()
    {
        PriorityQueue<int[]> dstQ = new PriorityQueue<>((e1, e2) -> {return Integer.compare(e1[1], e2[1]);});
        dstQ.offer(new int[]{C, 0});
        costArr[C] = 0;

        while (!dstQ.isEmpty())
        {
            int[] cur = dstQ.poll();

            int n = cur[0];
            int d = cur[1];

            if(d > costArr[n]) continue;

            cost = d; // 여기까지 올 수 있는 거리
            cnt++; // 해킹한 컴퓨터 +1 대

            for (int i = 0; i < edgeArr[n].size(); i++)
            {
                int nd = d + edgeArr[n].get(i).w;

                if(nd < costArr[edgeArr[n].get(i).e])
                {
                    costArr[edgeArr[n].get(i).e] = nd;
                    dstQ.offer(new int[]{edgeArr[n].get(i).e, nd});
                }
            }
        }
    }
}
