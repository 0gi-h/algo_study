import java.io.*;
import java.util.*;
public class Main {
    public static class Tab{
        int cpu;
        int memory;
        int priority;
        public Tab(int cpu, int memory, int priority){
            this.cpu = cpu;
            this.memory = memory;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Tab[] tabs  = new Tab[N];
        int cpuTotal = 0;
        int memoryTotal = 0;
        int priorityTotal = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cpu = Integer.parseInt(st.nextToken());
            int memory = Integer.parseInt(st.nextToken());
            int priority = Integer.parseInt(st.nextToken());
            tabs[i] = new Tab(cpu, memory, priority);
            cpuTotal += cpu;
            memoryTotal += memory;
            priorityTotal += priority;
        }

        if(cpuTotal < M || memoryTotal < K) System.out.println(-1);
        else{
            int[][][] dp = new int[N+1][M+1][priorityTotal+1];
            for(int i = 0; i < dp.length; i++){
                for(int j = 0; j < dp[0].length; j++){
                    Arrays.fill(dp[i][j], Integer.MIN_VALUE);
                }
            }
            dp[0][0][0] = 0;
            for(int i = 1; i <= N; i++){
                for(int j = 0; j < M+1; j++){
                    for(int k = 0;  k < priorityTotal+1; k++ ){
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k]);

                        int nextCpu = Math.min(M, j + tabs[i-1].cpu);

                        int nextPriority = k + tabs[i-1].priority;
                        if (nextPriority > priorityTotal) continue;

                        dp[i][nextCpu][nextPriority] = Math.max(dp[i][nextCpu][nextPriority], dp[i-1][j][k] + tabs[i-1].memory);
                    }
                }
            }

            int result = 0;
            for (int i=0; i<=priorityTotal; i++){
                if (dp[N][M][i] >= K){
                    result = i;
                    break;
                }
            }
            System.out.println(result);
        }

    }
}
