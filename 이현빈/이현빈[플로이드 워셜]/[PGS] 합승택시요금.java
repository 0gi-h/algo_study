import java.util.*;

class Solution 
{
    public int solution(int n, int s, int a, int b, int[][] fares) 
    {
        // 초기화
        final int INF = 20000000;
        int answer = INF;
        int[][] dist = new int[n + 1][n + 1];
   
        for(int i = 1; i <= n; i++) 
        {
            for(int j = 1; j <= n; j++) 
            {
                if(i == j) 
                    dist[i][j] = 0;
                else 
                    dist[i][j] = INF;
            }
        }
        
        // 간선 정보
        for(int[] fare : fares) 
        {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            
            dist[start][end] = cost;
            dist[end][start] = cost;
        }
        
        for(int k = 1; k <= n; k++) 
        {
            for(int i = 1; i <= n; i++) 
            {
                for(int j = 1; j <= n; j++) 
                {
                    if(dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        
        // 모든 지점을 합승 종료 지점으로 고려
        for(int i = 1; i <= n; i++)
        {
            int totalCost = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(answer, totalCost);
        }
        
        return answer;
    }
}
