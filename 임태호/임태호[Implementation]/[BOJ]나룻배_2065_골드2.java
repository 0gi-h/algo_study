import java.util.*;
import java.io.*;

public class Main {
    static class Passenger {
        int idx;        // 입력 순서
        int time;       // 도착 시간
        boolean isLeft; // 왼쪽 정박장인지

        Passenger(int idx, int time, boolean isLeft) {
            this.idx = idx;
            this.time = time;
            this.isLeft = isLeft;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        Queue<Passenger> left = new ArrayDeque<>();
        Queue<Passenger> right = new ArrayDeque<>();
        int[] arrivalTimes = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String side = st.nextToken();
            Passenger p = new Passenger(i, time, side.equals("left"));
            if (p.isLeft) left.offer(p);
            else right.offer(p);
        }

        int currentTime = 0;
        boolean isLeft = true; // 배는 왼쪽에서 시작

        while (!left.isEmpty() || !right.isEmpty()) {
            Queue<Passenger> currentSide = isLeft ? left : right;
            Queue<Passenger> otherSide = isLeft ? right : left;

            // 현재 위치에서 탑승할 수 있는 손님 태우기
            int count = 0;
            boolean hasPassenger = false;
            while (!currentSide.isEmpty() && currentSide.peek().time <= currentTime && count < m) {
                Passenger p = currentSide.poll();
                arrivalTimes[p.idx] = currentTime + t; // 도착 시간 기록
                count++;
                hasPassenger = true;
            }

            if (hasPassenger) {
                // 손님 태웠으면 반대편으로 이동
                currentTime += t;
                isLeft = !isLeft;
            } else {
                // 현재 위치에 손님이 없을 때
                int nextTime = Integer.MAX_VALUE;
                if (!currentSide.isEmpty()) nextTime = Math.min(nextTime, currentSide.peek().time);
                if (!otherSide.isEmpty()) nextTime = Math.min(nextTime, otherSide.peek().time);

                // 현재 시간보다 더 빠른 손님 도착 시간으로 이동
                if (currentTime < nextTime) {
                    currentTime = nextTime;
                }

                // 손님이 현재 위치에 없고, 다른 쪽 손님이 있으면 이동
                if (!otherSide.isEmpty() && otherSide.peek().time <= currentTime) {
                    currentTime += t;
                    isLeft = !isLeft;
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int time : arrivalTimes) {
            sb.append(time).append('\n');
        }
        System.out.print(sb);
    }
}
