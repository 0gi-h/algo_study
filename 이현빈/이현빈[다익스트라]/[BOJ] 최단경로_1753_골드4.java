import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;
    static final int INF = 123456789;

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
    static int startNode;
    static int[] costArr;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 간선 정보
        edgeArr = new List[V + 1];
        for (int i = 1; i <= V; i++)
            edgeArr[i] = new ArrayList<>();

        // 시작 점
        startNode = Integer.parseInt(br.readLine());

        // i번째 정점까지 도달 하는 최소 비용을 저장할 배열
        costArr = new int[V + 1];
        Arrays.fill(costArr, INF);

        for (int i = 0; i < E; i++)
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeArr[s].add(new Edge(e, w));
        }

        dijkstra();

        for (int i = 1; i < costArr.length; i++)
            sb.append(costArr[i] == INF ? "INF" : costArr[i]).append("\n");

        sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }

    private static void dijkstra()
    {
        // 다익스트라 초기화
        PriorityQueue<int[]> dstQ = new PriorityQueue<>((e1, e2) -> {return Integer.compare(e1[1], e2[1]);});
        costArr[startNode] = 0; // 시작점
        dstQ.offer(new int[]{startNode, 0});

        while (!dstQ.isEmpty())
        {
            int[] cur = dstQ.poll();

            int start = cur[0];
            int d = cur[1];

            if(d > costArr[start]) // 이미 저장된 값보다 크다면 갈 필요 없어요!
                continue;

            // 해당 정점에서 갈 수 있는 모든 간선 !
            for (int i = 0; i < edgeArr[start].size(); i++)
            {
                // 거기까지 가는 비용 가져오기
                int nd = d + edgeArr[start].get(i).w;

                if (nd < costArr[edgeArr[start].get(i).e]) // 거기까지 가는 비용이 저장된 비용보다 더 작은가
                {
                    costArr[edgeArr[start].get(i).e] = nd; // 그렇다면 업데이트
                    dstQ.offer(new int[]{edgeArr[start].get(i).e, nd});
                }
            }
        }
    }
}
