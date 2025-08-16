import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] peopleArr;
    static List<Integer>[] zoneList;
    static Deque<List<Integer>> caseQ;
    static int MIN;

    public static void main(String[] args) throws IOException
    {
        // 초기화
        N = Integer.parseInt(br.readLine());
        peopleArr = new int[N];
        zoneList = new List[N];
        caseQ = new ArrayDeque<>();
        MIN = Integer.MAX_VALUE;

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            peopleArr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());

            zoneList[i] = new ArrayList<>();

            for (int j = 0; j < count; j++)
                zoneList[i].add(Integer.parseInt(st.nextToken()));
        }

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) // 원본 배열 만들기
            arr[i] = i;

        boolean[] sel = new boolean[N]; // 선택 배열

        PowerSet(arr, sel, 0); // 뽑아!

        while (!caseQ.isEmpty())
        {
            List<Integer> first = caseQ.pollFirst();

            if (first.isEmpty() || first.size() == N) continue; // 공집합/전체집합 제외

            // 여집합(last) 구성을 위한 준비
            boolean[] inFirst = new boolean[N];

            for (int v : first) // fist에 들어있음 => 제 1구역
                inFirst[v] = true;

            List<Integer> last = new ArrayList<>();

            for (int i = 0; i < N; i++) // first에 안들어있음 => 제 2구역
            {
                if (!inFirst[i])
                    last.add(i);
            }

            // 두 구역 모두 연결인지 확인
            if (!isConnected(first)) // 1구역은 서로 안이어진다 == 할 필요 없다
                continue;

            if (!isConnected(last)) // 마찬가지
                continue;

            int sumA = 0, sumB = 0;

            for (int v : first)
                sumA += peopleArr[v];

            for (int v : last)
                sumB += peopleArr[v];

            MIN = Math.min(MIN, Math.abs(sumA - sumB)); // 인구수 갱신
        }

        System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
    }

    // 그룹이 내부에서 모두 이어지는지 확인
    private static boolean isConnected(List<Integer> group)
    {
        if (group.isEmpty()) // 빈그룹인데용
            return false;

        boolean[] inGroup = new boolean[N];

        for (int i = 0; i < group.size(); i++)
            inGroup[group.get(i)] = true;

        boolean[] vis = new boolean[N];
        Deque<Integer> q = new ArrayDeque<>();

        int start = group.get(0);
        vis[start] = true;
        q.offer(start);

        int reached = 1;

        while (!q.isEmpty())
        {
            int cur = q.poll();

            for (int nxt : zoneList[cur])
            {
                int ni = nxt - 1;

                if (!inGroup[ni] || vis[ni]) // 갈 필요가 없다.
                    continue;

                vis[ni] = true;
                reached++;
                q.offer(ni);
            }
        }
        return reached == group.size();
    }

    private static void PowerSet(int[] arr, boolean[] sel, int idx)
    {
        if (idx == N)
        {
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < N; i++)
                if (sel[i]) temp.add(arr[i]);

            caseQ.add(temp);

            return;
        }

        sel[idx] = true;
        PowerSet(arr, sel, idx + 1);

        sel[idx] = false;
        PowerSet(arr, sel, idx + 1);
    }
}
