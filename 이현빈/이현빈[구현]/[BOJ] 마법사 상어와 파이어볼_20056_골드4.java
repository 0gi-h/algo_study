import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K, sum;

    static int[] fdx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] fdy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class FireBall
    {
        int r, c, m, s, d;
        boolean isUse;

        public FireBall(int r, int c, int m, int s, int d)
        {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            isUse = true;
        }

        public void Move()
        {
            if(!isUse) return;

            r = (r + fdx[d] * (s % N) + N) % N;
            c = (c + fdy[d] * (s % N) + N) % N;
        }
    }

    static List<FireBall> fireballList;
    static List<FireBall>[][] map;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sum = 0;

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();

        // 파이어볼 생성
        fireballList = new ArrayList<>();
        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            FireBall fb = new FireBall(r,c,m,s,d);
            fireballList.add(fb);
            map[r][c].add(fb); // 이 자리에 fireball 이 하나 있다
        }

        for (int i = 0; i < K; i++)
        {
            // 맵 초기화
            for (int x = 0; x < N; x++)
                for (int y = 0; y < N; y++)
                    map[x][y].clear();

            // 파이어볼 이동
            for(FireBall fb : fireballList)
            {
                fb.Move();
                if(fb.isUse) map[fb.r][fb.c].add(fb);
            }

            // 파이어볼 합치기
            CheckFireBall();

            List<FireBall> newList = new ArrayList<>();
            for (FireBall fb : fireballList)
                if (fb.isUse) newList.add(fb);
            fireballList = newList;
        }

        // 질량의 합
        for(FireBall fb : fireballList)
        {
            if(fb.isUse)
                sum += fb.m;
        }

        System.out.println(sum);
    }

    private static void CheckFireBall()
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if(map[i][j].size() > 1) // 이 자리에 fireball 이 2개 이상인가?
                {
                    List<FireBall> tempList = map[i][j]; // 합쳐야될것들

                    for(FireBall fb : tempList)
                    {
                        //fireballList.remove(fb); // 이제 여기에 없어야해
                        fb.isUse = false; // 이제 안써요 이건
                    }

                    // 다음 질량, 속력 구하기
                    int nm = 0;
                    int ns = 0;
                    for(FireBall fb : tempList)
                    {
                        nm += fb.m;
                        ns += fb.s;
                    }

                    nm /= 5; // 질량
                    if (nm == 0) continue;

                    ns /= tempList.size(); // 속력

                    boolean allEven = true;
                    boolean allOdd = true;

                    for (FireBall fb : tempList)
                    {
                        if (fb.d % 2 == 0) allOdd = false;
                        else allEven = false;
                    }

                    if (allEven || allOdd) even(tempList, nm, ns);
                    else odd(tempList, nm, ns);
                }
            }
        }
    }

    private static void even(List<FireBall> tempList, int nm, int ns)
    {
        int d = 0;
        for (int i = 0; i < 4; i++)
        {
            FireBall newFb = new FireBall(tempList.get(0).r, tempList.get(0).c, nm, ns, d);
            fireballList.add(newFb);
            map[tempList.get(0).r][tempList.get(0).c].add(newFb);
            d+=2;
        }
    }

    private static void odd(List<FireBall> tempList, int nm, int ns)
    {
        int d = 1;
        for (int i = 0; i < 4; i++)
        {
            FireBall newFb = new FireBall(tempList.get(0).r, tempList.get(0).c, nm, ns, d);
            fireballList.add(newFb);
            map[tempList.get(0).r][tempList.get(0).c].add(newFb);
            d+=2;
        }
    }
}
