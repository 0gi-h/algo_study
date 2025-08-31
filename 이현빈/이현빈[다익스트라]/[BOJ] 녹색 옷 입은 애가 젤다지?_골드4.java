import java.io.*;
import java.util.*;

public class Main
{	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, T;
	static int[][] mapArr;
	static int[][] costArr;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException
	{
		T = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			
			mapArr = new int[N][N];
			costArr = new int[N][N];
			
			for (int i = 0; i < N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mapArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dijkstra();
			sb.append("Problem ").append(T).append(": ").append(costArr[N-1][N-1]).append("\n");
			T++;
		}
		if(sb.length() > 0)
			sb.setLength(sb.length() - 1);
		
		System.out.println(sb);
	}
	
	private static void dijkstra() 
	{
		PriorityQueue<int[]> dijkstraQ = new PriorityQueue<>((a, b) -> {return Integer.compare(a[0], b[0]);});
		dijkstraQ.offer(new int[] {mapArr[0][0], 0, 0});
		
    for (int i = 0; i < N; i++)
      Arrays.fill(costArr[i], 123456789);
        
		costArr[0][0] = mapArr[0][0];
		
		while(!dijkstraQ.isEmpty())
		{
			int[] cur = dijkstraQ.poll();
			
			int cost = cur[0];
			int x = cur[1];
			int y = cur[2];
			
			if(cost > costArr[x][y]) 
				continue;
			
			if(x == N - 1 && y == N - 1)
				return;
			
			for (int i = 0; i < dx.length; i++) 
			{
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < N)
				{
					int nCost = cost + mapArr[nx][ny];
					if(nCost < costArr[nx][ny])
					{
						costArr[nx][ny] = nCost;
						dijkstraQ.offer(new int[] {nCost, nx, ny});
					}
				}
			}
		}
	}

	static void printArr()
	{
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(mapArr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

