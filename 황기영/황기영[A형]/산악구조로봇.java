package ssafy;

import java.io.*;
import java.util.*;

public class 산악구조로봇 
{
	static class Point implements Comparable<Point>	{
		int r;
		int c;
		int height;
		int fuel;
		
		Point(int r, int c, int height, int fuel)		{
			this.r = r;
			this.c = c;
			this.height = height;
			this.fuel = fuel;
		}
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.fuel, o.fuel);
		}
	}
	static int N;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int minFuel;
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= t; tc++)
		{
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			minFuel = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
				{
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			BFS(0, 0);
			System.out.println("#" + tc + " " + minFuel);
		}
	}
	
	private static void BFS(int r, int c)
	{
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		
		boolean[][][] visited = new boolean[N][N][4];
		
		visited[r][c][0] = true;
		pq.add(new Point(r, c, map[r][c], 0));
		
		while (!pq.isEmpty())
		{
			Point cur = pq.poll();
			if (cur.r == N - 1 && cur.c == N - 1)
			{
				minFuel = Math.min(minFuel, cur.fuel);
				return;
			}
			for (int i = 0; i < 4; i++)
			{
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (!visited[nr][nc][i])
				{
					visited[nr][nc][i] = true;
					pq.add(new Point(nr, nc, map[nr][nc], cur.fuel + GetFuel(map[cur.r][cur.c], map[nr][nc])));
				}
			}
		}
	}
	
	private static int GetFuel(int start, int end)
	{
		if (start == end) return 1;
		else if (start < end) return (end - start) * 2;
		else return 0;
	}

}
