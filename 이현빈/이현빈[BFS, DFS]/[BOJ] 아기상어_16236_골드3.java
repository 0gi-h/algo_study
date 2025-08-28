import java.io.*;
import java.util.*;

public class Main
{
	static class Shark
	{
		int x, y, s, eatCount;
		
		Shark(int x, int y, int s)
		{
			this.x = x;
			this.y = y;
			this.s = s;
		}
		// 좌표
		public int[] GetSharkPos()
		{
			return new int[] {x, y};
		}
		
		public void SetSharkPos(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		// 크기
		public void SetSharkSize(int s)
		{
			this.s = s;
		}
	}
	
	static class Fish
	{
		int x, y, s;
		
		Fish(int x, int y, int s)
		{
			this.x = x;
			this.y = y;
			this.s = s;
		}
		
		public int[] GetFishPos()
		{
			return new int[] {x, y};
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, totalDist, dist;
	static int[][] mapArr;
	static Shark shark;
	static List<Fish> fishList;
	
	static int[] sharkPos;
	static int[] fishPos;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException
	{
		// 초기화
		N = Integer.parseInt(br.readLine());
		fishList = new ArrayList<>();
		mapArr = new int[N][N];
		sharkPos = new int[2];
		fishPos = new int[2];
		totalDist = 0;
		
		// 입력
		for (int i = 0; i < N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) 
			{
				mapArr[i][j] = Integer.parseInt(st.nextToken());
				
				if(mapArr[i][j] == 9)
				{
					shark = new Shark(i, j, 2); // 상어 생성
				}
				else if(mapArr[i][j] >= 1 && mapArr[i][j] <= 6)
				{
					Fish fish = new Fish(i, j, mapArr[i][j]);
					fishList.add(fish);
				}
			}
		}
		
		// 인덱스로 접근하면 ㅈ될거같은데...
		while(!fishList.isEmpty())
		{
			dist = 0;
			
			// 1. 먹을 먹이 찾기
			Fish target = FindFish();
			
			// 2. 먹으러 가기 -> 먹으면서 fishList에서 삭제
			if(target != null)
			{
				//System.out.println("이거 먹으러 갑니다  " + target.x + " " + target.y + " .. 거리 : " + dist);
				mapArr[target.x][target.y] = 9; // 냠
				mapArr[sharkPos[0]][sharkPos[1]] = 0; // 기존 상어 자리는 빈 칸
				shark.SetSharkPos(target.x, target.y); // 상어 위치 갱신
				totalDist += dist; // 이동 거리
				
				// 3. 크기 갱신
				shark.eatCount++;
				if(shark.eatCount == shark.s)
				{
					//System.out.println("크기가 커졌어용");
					shark.s++; // 크기가 커졌어용
					shark.eatCount = 0;
				}
				
				fishList.remove(target);
			}
			else
			{
				break;
			}
		}
		
		System.out.println(totalDist);
	}
	
	static Fish FindFish()
	{
		Fish target = null;
		// 1. 가장 가까운 물고기 찾기 -> | Xs - Xf | + | Ys - Yf| 가 먹이 까지의 거리
		
		// 1-1 그게 여러개라면 가장 위
		
		// 1-2. 그것도 여러개라면 왼쪽
		sharkPos = shark.GetSharkPos();
		
		int min = Integer.MAX_VALUE;
		int minX = 0, minY = 0; // 위, 왼 을 비교하기 위한 변수
		int calcDist = 0;
		for (int i = 0; i < fishList.size(); i++) 
		{
			Fish fish = fishList.get(i);
			fishPos = fish.GetFishPos();
			
			// 먹을 수 없는 먹이에요 !!
			if(shark.s <= fish.s)
				continue;
			
			// 여기 먹이까지의 거리를 BFS로 돌아야할것같다.
			calcDist = DistBfs();
			//System.out.println("먹이까지의 거리 " + calcDist);
			//int calcDist = Math.abs(sharkPos[0] - fishPos[0]) + Math.abs(sharkPos[1] - fishPos[1]);
			//System.out.println("먹으러 갈 놈 " + calcDist);
			
			if(calcDist == -1)
				continue;
			
			if(min > calcDist)
			{
				min = calcDist;
				minX = fishPos[0];
				minY = fishPos[1];
				target = fish; // 얘가 먹으러 갈 먹이
			}
			else if(calcDist == min)
			{
				// 새로운 애가 더 위에 있는 먹이다.
				if(minX > fishPos[0])
				{
					min = calcDist;
					minX = fishPos[0];
					minY = fishPos[1];
					target = fish; // 얘가 먹으러 갈 먹이
				}
				else if(minX == fishPos[0]) // 얘도 위에 있는 먹이인데??
				{
					// 새로운 애가 더 왼쪽에 있는 먹이다.
					if(minY > fishPos[1])
					{
						min = calcDist;
						minX = fishPos[0];
						minY = fishPos[1];
						target = fish; // 얘가 먹으러 갈 먹이
					}
				}
			}
		}
		
		// 타겟이 먹을 놈
		if(target != null)
		{
			//System.out.println("현위치 " + shark.x + " " + shark.y);
			dist = min;
			return target;
		}
		return null;
	}
	
	static int DistBfs()
	{
		Deque<int[]> bfsQ = new ArrayDeque<>();
		bfsQ.offer(new int[] {shark.x, shark.y});
		boolean[][] check = new boolean[N][N];
		check[shark.x][shark.y] = true;
		int[][] distArr = new int[N][N];
		distArr[shark.x][shark.y] = 0;
		int min = Integer.MAX_VALUE;
		
		while(!bfsQ.isEmpty())
		{
			int[] pos = bfsQ.poll();
				
			if(pos[0] == fishPos[0] && pos[1] == fishPos[1])
			{
				//System.out.println("뽑아 ! " +  distArr[pos[0]][pos[1]]);
				min = Math.min(min, distArr[pos[0]][pos[1]]);
			}
				
			for (int i = 0; i < dx.length; i++) 
			{
				int nx = pos[0] + dx[i];
				int ny = pos[1] + dy[i];
					
				// 크기가 같은 먹이는 지나갈 수는 있음
				if(nx >= 0 && ny >= 0 && nx < N && ny < N && !check[nx][ny] && mapArr[nx][ny] <= shark.s)
				{
					check[nx][ny] = true;
					distArr[nx][ny] = distArr[pos[0]][pos[1]] + 1;
					bfsQ.offer(new int[] {nx,ny});
				}
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}
	
	static void PrintArr()
	{
		for (int i = 0; i < mapArr.length; i++) {
			System.out.println(Arrays.toString(mapArr[i]));
		}
	}
}
