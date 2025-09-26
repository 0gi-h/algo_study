import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;

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
    static int[] costArr;
    static int start, end, MAX;
    static final int INF = 1123456789;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAX = 0;

        edgeArr = new List[N + 1];
        for (int i = 1; i <= N; i++)
            edgeArr[i] = new ArrayList<>();

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeArr[s].add(new Edge(e, w));
            edgeArr[e].add(new Edge(s, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(MAX);
    }

    private static void dijkstra()
    {
        PriorityQueue<int[]> dstQ = new PriorityQueue<>((e1, e2) -> {return Integer.compare(e2[1], e1[1]);});
        dstQ.offer(new int[]{start, INF});
        costArr = new int[N + 1];
        Arrays.fill(costArr, 0);
        costArr[start] = INF;

        while (!dstQ.isEmpty())
        {
            int[] cur = dstQ.poll();
            int n = cur[0];
            int c = cur[1];

            if (c < costArr[n]) continue;

            if (n == end)
            {
                MAX = Math.max(MAX, c);
                return;
            }

            for (int i = 0; i < edgeArr[n].size(); i++)
            {
                int nc = Math.min(c, edgeArr[n].get(i).w);

                if (nc > costArr[edgeArr[n].get(i).e])
                {
                    costArr[edgeArr[n].get(i).e] = nc;
                    dstQ.offer(new int[]{edgeArr[n].get(i).e, nc});
                }
            }
        }
    }
}
