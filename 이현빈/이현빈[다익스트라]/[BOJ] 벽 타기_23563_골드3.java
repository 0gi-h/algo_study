import java.io.*;
import java.util.*;

public class Main
{    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int H, W;
    static char[][] mapArr;
    static int[] startPos;
    static int[] endPos;
    
    static boolean[][] isWallAdjacentArr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int[][] costArr;
    static final int INF = 123456789;
    
    public static void main(String[] args) throws IOException
    {
    	st = new StringTokenizer(br.readLine());
    	H = Integer.parseInt(st.nextToken());
    	W = Integer.parseInt(st.nextToken());
    	
    	mapArr = new char[H][W];
    	startPos = new int[2];
    	endPos = new int[2];
    	isWallAdjacentArr = new boolean[H][W];
    	costArr = new int[H][W];
    	for(int i = 0; i < H; i++)
    		Arrays.fill(costArr[i], INF);
    	
    	for (int i = 0; i < H; i++) 
    	{
    		String str = br.readLine();
    		
    		for (int j = 0; j < str.length(); j++)
    		{
    			mapArr[i][j] = str.charAt(j);
    			if(mapArr[i][j] == 'S')
    				startPos = new int[] {i, j};
    			if(mapArr[i][j] == 'E')
    				endPos = new int[] {i, j};
    		}
		}
    	
    	// 인접한 칸 찾기
        for(int i = 0; i < H; i++)
        {
            for(int j = 0; j < W; j++)
            {
                if(mapArr[i][j] != '#')
                {
                    for(int k = 0; k < 4; k++)
                    {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        
                        if(ni >= 0 && ni < H && nj >= 0 && nj < W && mapArr[ni][nj] == '#')
                        {
                            isWallAdjacentArr[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        
    	System.out.println(dijkstra());
    }
    
    private static int dijkstra() 
    {
    	PriorityQueue<int[]> dstQ = new PriorityQueue<>((a1, b1) -> {return Integer.compare(a1[2], b1[2]);});
    	dstQ.offer(new int[] {startPos[0], startPos[1], 0});
    	boolean[][] visitedArr = new boolean[H][W];
    	
    	while(!dstQ.isEmpty())
    	{
    		int[] cur = dstQ.poll();
    		int x = cur[0];
    		int y = cur[1];
    		int c = cur[2];
    		
    		if(c > costArr[x][y]) continue;
    		
    		if(visitedArr[x][y]) continue;
    		visitedArr[x][y] = true;
    		
    		if(x == endPos[0] && y == endPos[1])
    			return c;
    		
    		for(int i = 0; i < dx.length; i++)
    		{
    			int nx = x + dx[i];
    			int ny = y + dy[i];
    			
    			if(nx >= 0 && ny >= 0 && nx < H && ny < W && mapArr[nx][ny] != '#' && !visitedArr[nx][ny])
    			{
    				int nc;
    				
    				if(isWallAdjacentArr[nx][ny] && isWallAdjacentArr[x][y])
    					nc = c;
    				else
    					nc = c + 1;
    				
    				if(costArr[nx][ny] > nc)
    				{
    					costArr[nx][ny] = nc;
    					dstQ.offer(new int[] {nx,ny,nc});
    				}
    			}
    		}
    	}
    	return -1;
	}

	static void PrintArr()
    {
    	for (int i = 0; i < H; i++) 
    	{
    		for (int j = 0; j < W; j++) 
    			System.out.print(mapArr[i][j]);
    		System.out.println();
		}
    	
    	System.out.println(Arrays.toString(startPos));
    	System.out.println(Arrays.toString(endPos));
    }
}
