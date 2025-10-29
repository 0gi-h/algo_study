import java.io.*;
import java.util.*;

public class Main
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Ground
    {
        int x, y, food, idx;
        PriorityQueue<Tree> treePQ;
        Deque<Tree> deadTreeDeq;

        public Ground(int x, int y)
        {
            idx = 0;
            this.x = x;
            this.y = y;
            food = 5; // 초기엔 5만큼
            treePQ = new PriorityQueue<>(new Comparator<Tree>()
            {
                @Override
                public int compare(Tree o1, Tree o2)
                {
                    return Integer.compare(o1.age, o2.age);
                }
            });

            deadTreeDeq = new ArrayDeque<>(); // 죽은 나무 저장할 곳
        }

        // 나무 추가
        public void PutTree(int age)
        {
            treePQ.offer(new Tree(idx, age));
            idx++;
        }

        // 봄
        public void Spring()
        {
            // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
            // 하나의 칸에 여러개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.

            PriorityQueue<Tree> tempQ = new PriorityQueue<>(new Comparator<Tree>()
            {
                @Override
                public int compare(Tree o1, Tree o2)
                {
                    return Integer.compare(o1.age, o2.age);
                }
            });

            while(!treePQ.isEmpty())
            {
                Tree cur = treePQ.poll();

                if(cur.age <= food)
                {
                    food -= cur.age;
                    cur.age++;
                    tempQ.offer(cur);
                }
                else
                    deadTreeDeq.offer(cur);
            }

            treePQ = tempQ;
        }

        // 여름
        public void Summer()
        {
            // 여름에는 봄에 죽은 나무가 양분으로.
            // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
            while(!deadTreeDeq.isEmpty())
            {
                Tree cur = deadTreeDeq.poll();

                food += cur.age / 2;
            }
        }

        // 가을
        public void Autumn()
        {
            // 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
            PriorityQueue<Tree> tempQ = new PriorityQueue<>(new Comparator<Tree>()
            {
                @Override
                public int compare(Tree o1, Tree o2)
                {
                    return Integer.compare(o1.age, o2.age);
                }
            });

            while (!treePQ.isEmpty())
            {
                Tree cur = treePQ.poll();

                if(cur.age % 5 == 0) // 번식 하고 넘겨
                {
                    for(int i = 0; i < dx.length; i++)
                    {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if(nx >= 0 && ny >= 0 && nx < N && ny < N)
                            groundArr[nx][ny].PutTree(1);
                    }
                }
                tempQ.offer(cur);
            }

            treePQ = tempQ;
        }

        // 겨울
        public void Winter(int food)
        {
            // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
            this.food += food;
        }
    }

    static class Tree
    {
        int idx, age;

        public Tree(int idx, int age)
        {
            this.idx = idx;
            this.age = age;
        }
    }

    static int N, M, K, treeCnt;
    static int[][] s2d2Arr;
    static Ground[][] groundArr;

    static int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 초기화
        groundArr = new Ground[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                groundArr[i][j] = new Ground(i, j);
        }
        s2d2Arr = new int[N][N];
        treeCnt = 0;

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                s2d2Arr[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            groundArr[x][y].PutTree(age);
        }

        for (int i = 0; i < K; i++)
        {
            // 봄
            for (int j = 0; j < N; j++)
            {
                for (int k = 0; k < N; k++)
                {
                    if(!groundArr[j][k].treePQ.isEmpty())
                        groundArr[j][k].Spring();
                }
            }

            // 여름
            for (int j = 0; j < N; j++)
            {
                for (int k = 0; k < N; k++)
                    groundArr[j][k].Summer();
            }

            // 가을
            for (int j = 0; j < N; j++)
            {
                for (int k = 0; k < N; k++)
                {
                    if(!groundArr[j][k].treePQ.isEmpty())
                        groundArr[j][k].Autumn();
                }
            }

            // 겨울
            for (int j = 0; j < N; j++)
            {
                for (int k = 0; k < N; k++)
                    groundArr[j][k].Winter(s2d2Arr[j][k]);
            }
        }

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                treeCnt += groundArr[i][j].treePQ.size();
        }

        System.out.println(treeCnt);
    }
}
