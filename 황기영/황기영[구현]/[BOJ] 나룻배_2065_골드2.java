import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static class Wait {
        int time;
        String place;
        int index;

        Wait(int time, String place, int index) {
            this.time = time;
            this.place = place;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        ArrayDeque<Wait> lq = new ArrayDeque<>();
        ArrayDeque<Wait> rq = new ArrayDeque<>();

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            String place = st.nextToken();

            if (place.equals("left")) {
                lq.offer(new Wait(time, place, i));
            } else {
                rq.offer(new Wait(time, place, i));
            }
        }

        int ct = 0;
        String cp = "left";

        while (!lq.isEmpty() || !rq.isEmpty()) {
            boolean moved = false;

            if (cp.equals("left")) {
                if (!lq.isEmpty() && lq.peekFirst().time <= ct) {
                    int count = 0;
                    while (!lq.isEmpty() && lq.peekFirst().time <= ct && count < m) {
                        Wait cur = lq.poll();
                        result[cur.index] = ct + t;
                        count++;
                    }
                    ct += t;
                    cp = "right";
                    moved = true;
                }
            } else {
                if (!rq.isEmpty() && rq.peekFirst().time <= ct) {
                    int count = 0;
                    while (!rq.isEmpty() && rq.peekFirst().time <= ct && count < m) {
                        Wait cur = rq.poll();
                        result[cur.index] = ct + t;
                        count++;
                    }
                    ct += t;
                    cp = "left";
                    moved = true;
                }
            }

            if (!moved) {
                int nextLeft = lq.isEmpty() ? Integer.MAX_VALUE : lq.peekFirst().time;
                int nextRight = rq.isEmpty() ? Integer.MAX_VALUE : rq.peekFirst().time;
                int nextTime = Math.min(nextLeft, nextRight);

                if (nextTime > ct) {
                    ct = nextTime;
                } else {
                    ct += t;
                    cp = cp.equals("left") ? "right" : "left";
                }
            }
        }

        for (int time : result) {
            System.out.println(time);
        }
    }
}
