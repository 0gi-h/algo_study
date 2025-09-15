import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length][];
        
        dp[0] = new int[1];
        dp[0][0] = triangle[0][0];
        for(int i = 1; i < triangle.length-1; i++){
            dp[i] = new int[i+1];
            for(int j = 0; j < i+1; j++){
                if(j <= i-1) dp[i][j] = dp[i-1][j];
                if(j - 1 >= 0) dp[i][j] = Math.max(dp[i][j] , dp[i-1][j-1]);
                
                dp[i][j] += triangle[i][j];
            }
        }
        dp[triangle.length-1] = new int[triangle.length];
        //max 연산에 반복문을 생성하는 것을 방지하기 위해 마지막 위치만 따로 반복문으로 dp 연산 했습니다.
        for(int j = 0; j < triangle.length; j++){
            if(j <= triangle.length-2) dp[triangle.length-1][j] = dp[triangle.length-2][j];
            if(j - 1 >= 0) dp[triangle.length-1][j] = Math.max(dp[triangle.length-1][j] , dp[triangle.length-2][j-1]);
            dp[triangle.length-1][j] += triangle[triangle.length-1][j];
            answer = Math.max(dp[triangle.length-1][j], answer);
        }
        return answer;
    }
}
