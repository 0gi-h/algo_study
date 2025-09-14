class Solution 
{
    public int solution(int[][] triangle) 
    {
        // dp 만들기 ~
        int[][] dp = new int[triangle.length][triangle.length];
        
        // 마지막 줄 복사
        for (int i = 0; i < triangle.length; i++) 
            dp[triangle.length-1][i] = triangle[triangle.length-1][i];
        
        // bottom - up
        for(int i = triangle.length - 2; i >= 0; i--)
        {
            for (int j = 0; j <= i; j++)
                dp[i][j] = triangle[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
        }
        
        return dp[0][0];
    }
}
