import java.io.*;
import java.util.*;

public class Main {
    static int N, M, MAX;
    static int[][] mapArr;

    static class Sky
    {
        int[][][] shapes = {
                {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
                {{0, 0}, {1, 0}, {2, 0}, {3, 0}}};
    }

    static class Yellow
    {
        int[][][] shapes = {{{0, 0}, {0, 1}, {1, 0}, {1, 1}}};
    }

    static class Orange
    {
        int[][][] shapes = {
                {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
                {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
                {{1, 0}, {1, 1}, {1, 2}, {0, 2}},

                {{0, 1}, {1, 1}, {2, 1}, {2, 0}},
                {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
                {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
                {{0, 0}, {0, 1}, {0, 2}, {1, 2}}
        };
    }

    static class Green
    {
        int[][][] shapes = {
                {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
                {{0, 1}, {1, 0}, {1, 1}, {2, 0}},
                {{0, 1}, {0, 2}, {1, 0}, {1, 1}},
                {{0, 0}, {1, 0}, {1, 1}, {2, 1}}
        };
    }

    static class Pink
    {
        int[][][] shapes = {
                {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
                {{0, 1}, {1, 0}, {1, 1}, {2, 1}},
                {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
                {{0, 0}, {1, 0}, {2, 0}, {1, 1}}};
    }

    public static List<int[][][]> blockList;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAX = 0;

        mapArr = new int[N][M];

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                mapArr[i][j] = Integer.parseInt(st.nextToken());
        }

        blockList = new ArrayList<>();

        blockList.add(new Sky().shapes);
        blockList.add(new Yellow().shapes);
        blockList.add(new Orange().shapes);
        blockList.add(new Green().shapes);
        blockList.add(new Pink().shapes);

        Simulation(blockList);

        System.out.println(MAX);
    }

    static void Simulation(List<int[][][]> blocks)
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                for (int[][][] block : blocks)
                {
                    for (int[][] shape : block)
                    {
                        int sum = 0;

                        boolean isCan = true;

                        for (int[] b : shape)
                        {
                            int x = i + b[0];
                            int y = j + b[1];

                            if (x < 0 || y < 0 || x >= N || y >= M)
                            {
                                isCan = false;
                                break;
                            }

                            sum += mapArr[x][y];
                        }

                        if (isCan)
                            MAX = Math.max(MAX, sum);
                    }
                }
            }
        }
    }
}
