import java.io.*;
import java.util.*;

public class Main
{    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M, K;
    
    static class Tab
    {
    	int cpu, memory, importance;

		public Tab(int cpu, int memory, int importance) 
		{
			this.cpu = cpu;
			this.memory = memory;
			this.importance = importance;
		}
    }
    
    static Tab[] tabArr;
    
    public static void main(String[] args) throws IOException
    {
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	tabArr = new Tab[N + 1];
    	
    	for(int i = 1; i <= N; i++)
    	{
    		st = new StringTokenizer(br.readLine());
    		
    		int cpu = Integer.parseInt(st.nextToken());
    		int memory = Integer.parseInt(st.nextToken());
    		int importance = Integer.parseInt(st.nextToken());
    		
    		tabArr[i] = new Tab(cpu, memory, importance);
    	}
    	
    	int[][] dp = new int[M + 1][501];
    	
    	for(int i = 0; i <= M; i++)
    	{
    		Arrays.fill(dp[i], Integer.MIN_VALUE);
    	}
    	
    	dp[0][0] = 0;
    	
    	for(int i = 1; i <= N; i++)
    	{
    		Tab tab = tabArr[i];
    		
    		for(int j = M; j >= 0; j--)
    		{
    			for(int p = 500; p >= 0; p--)
    			{
    				int nextCpu = Math.min(M, j + tab.cpu);
    				int nextImportance = p + tab.importance;
    				
    				if(nextImportance <= 500 && dp[j][p] != Integer.MIN_VALUE)
    				{
    					dp[nextCpu][nextImportance] = Math.max(
    						dp[nextCpu][nextImportance],
    						dp[j][p] + tab.memory
    					);
    				}
    			}
    		}
    	}
    	
    	int answer = -1;
    	
    	for(int i = 0; i <= 500; i++)
    	{
    		if(dp[M][i] >= K)
    		{
    			answer = i;
    			break;
    		}
    	}
    	
    	System.out.println(answer);
    }
}
