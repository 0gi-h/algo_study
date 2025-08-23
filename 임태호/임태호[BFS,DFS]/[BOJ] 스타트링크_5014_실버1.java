import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        boolean[] v = new boolean[f+1];

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{s, 0});
        v[s] = true;
        int result = -1;
        while(deque.size() > 0){
            int[] now = deque.poll();
            if(now[0] == g){
                result = now[1];
                break;
            }
            // 위층
            int nU = now[0] + u;
            // 아래 층
            int nD = now[0] - d;

            // 위층 아래 층 연산은 따로 둬야 한다.
            if( !(nU > f || v[nU]) ){
                deque.offer(new int[]{nU, now[1]+1});
                v[nU] = true;
            }
            if( !(nD < 1 || v[nD])) {
                deque.offer(new int[]{nD, now[1] + 1});
                v[nD] = true;
            }
        }
        System.out.println(result == -1 ? "use the stairs" : result);
    }
}
