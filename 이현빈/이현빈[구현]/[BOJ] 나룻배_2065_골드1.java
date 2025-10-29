import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Deque<int[]> leftDeq;
    static Deque<int[]> rightDeq;
    static Deque<int[]> moveDeq;

    static int N, t, M, curTime, boatPos;
    static int[] answer;

    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());

        // 초기화
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        curTime = 0;
        leftDeq = new ArrayDeque<>();
        rightDeq = new ArrayDeque<>();
        boatPos = 0; // 0 : 왼쪽, 1 : 오른쪽
        moveDeq = new ArrayDeque<>();
        answer = new int[N];

        for (int i = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String type = st.nextToken();

            if(type.equals("left")) // 왼쪽에 손님
                leftDeq.offer(new int[]{time, i});
            else if(type.equals("right")) // 오른쪽에 손님
                rightDeq.offer(new int[]{time, i});
        }

        while(!leftDeq.isEmpty() || !rightDeq.isEmpty())
        {
            // 지금 나룻배는 왼쪽에 있다.
            if(boatPos == 0)
            {
                // 왼쪽에 손님이 있다.
                if(!leftDeq.isEmpty() && leftDeq.peek()[0] <= curTime)
                {
                    for (int i = 0; i < M; i++)
                    {
                        if(!leftDeq.isEmpty() && leftDeq.peek()[0] <= curTime)
                            moveDeq.offer(leftDeq.poll());
                    }
                }
                else if(!rightDeq.isEmpty() && rightDeq.peek()[0] <= curTime) // 오른쪽에 손님이 있다
                {
                    // 오른쪽으로 이동
                    curTime += t;
                    boatPos = 1;
                    continue;
                }
            }
            else if(boatPos == 1)
            {
                // 오른쪽에 손님이 있다.
                if(!rightDeq.isEmpty() && rightDeq.peek()[0] <= curTime)
                {
                    for (int i = 0; i < M; i++)
                    {
                        if(!rightDeq.isEmpty() && rightDeq.peek()[0] <= curTime) // 손님 태워
                            moveDeq.offer(rightDeq.poll());
                    }
                }
                else if(!leftDeq.isEmpty() && leftDeq.peek()[0] <= curTime) // 왼쪽에 손님
                {
                    // 왼쪽으로 이동
                    curTime += t;
                    boatPos = 0;
                    continue;
                }
            }

            // 손님을 태웠다면
            if(moveDeq.size() > 0)
            {
                // 이동
                curTime += t;
                boatPos = boatPos == 0 ? 1 : 0; // 보트 이동

                // 내림
                while(!moveDeq.isEmpty())
                {
                    int[] passenger = moveDeq.poll();
                    answer[passenger[1]] = curTime;
                }
            }
            else if(moveDeq.size() == 0)
            {
                // 다음 손님 어디야
                int nextTime = Integer.MAX_VALUE;
                
                if(!leftDeq.isEmpty())
                    nextTime = Math.min(nextTime, leftDeq.peek()[0]);
                if(!rightDeq.isEmpty())
                    nextTime = Math.min(nextTime, rightDeq.peek()[0]);

                if(nextTime != Integer.MAX_VALUE)
                    curTime = nextTime;
            }
        }

        for (int i = 0; i < N; i++)
            sb.append(answer[i]).append("\n");

        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);

        System.out.println(sb);
    }
}
