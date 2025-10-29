import java.io.*;
import java.util.*;

public class Main {    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static class Mutal
    {
    	int faDmg, saDmg, taDmg;
    	int[][] attackOrders;
    	
    	public Mutal()
    	{
    		faDmg = 9;
    		saDmg = 3;
    		taDmg = 1;
    		
    		attackOrders = new int[][] {
    			{faDmg, saDmg, taDmg}, {faDmg, taDmg, saDmg},
    			{saDmg, faDmg, taDmg}, {saDmg, taDmg, faDmg},
    			{taDmg, faDmg, saDmg}, {taDmg, saDmg, faDmg}
    		};
    	}
    }
    
    static class SCV
    {
    	int hp;
    	
    	public SCV(int hp)
    	{
    		this.hp = hp;
    	}
    }
    
    static class State
    {
    	int hp0, hp1, hp2, cnt;
    	
    	public State(int hp0, int hp1, int hp2, int cnt)
    	{
    		this.hp0 = hp0;
    		this.hp1 = hp1;
    		this.hp2 = hp2;
    		this.cnt = cnt;
    	}
    }
    
    static SCV[] scvArr;
    static Mutal mutal;
    static int N, cnt;
    static boolean[][][] visited;
    
    public static void main(String[] args) throws IOException
    {
    	N = Integer.parseInt(br.readLine());
    	scvArr = new SCV[3];
    	st = new StringTokenizer(br.readLine());
    	
    	for(int i = 0; i < N; i++)
    		scvArr[i] = new SCV(Integer.parseInt(st.nextToken()));
    	
    	for(int i = N; i < 3; i++)
    		scvArr[i] = new SCV(0);
    	
    	mutal = new Mutal();
    	visited = new boolean[61][61][61];
    	
    	cnt = BFS();
    	
    	System.out.println(cnt);
    }

	private static int BFS() 
	{
		Queue<State> queue = new LinkedList<>();
		State start = new State(scvArr[0].hp, scvArr[1].hp, scvArr[2].hp, 0);
		queue.offer(start);
		visited[start.hp0][start.hp1][start.hp2] = true;
		
		while(!queue.isEmpty())
		{
			State cur = queue.poll();
			
			if(CheckAllZero(cur))
				return cur.cnt;
			
			for(int[] order : mutal.attackOrders)
			{
				int nh0 = Math.max(0, cur.hp0 - order[0]);
				int nh1 = Math.max(0, cur.hp1 - order[1]);
				int nh2 = Math.max(0, cur.hp2 - order[2]);
				
				if(!visited[nh0][nh1][nh2])
				{
					visited[nh0][nh1][nh2] = true;
					queue.offer(new State(nh0, nh1, nh2, cur.cnt + 1));
				}
			}
		}
		return -1;
	}

	private static boolean CheckAllZero(State state) 
	{
		return state.hp0 == 0 && state.hp1 == 0 && state.hp2 == 0;
	}
}
