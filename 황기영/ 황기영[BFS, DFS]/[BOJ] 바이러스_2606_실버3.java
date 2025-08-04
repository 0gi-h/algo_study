import java.util.*;

public class Main {
	static int box[][]; 
	static int check[]; 
	static void bfs(int start) { 
		Queue<Integer> queue = new LinkedList<>();
		
		check[start] =1;
		queue.offer(start);
		int cnt = 0; 
		while(!queue.isEmpty()) {
			int x = queue.poll(); 
			
			for(int i=1;i<box.length;i++) { 
				if(box[x][i]==1 && check[i]!=1) { 
					queue.offer(i);
					check[i] = 1;
					cnt++;
				}
			}
		}
		System.out.println(cnt); 
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		
		int n= sc.nextInt(); 
		int m = sc.nextInt(); 
		
		box=new int[n+1][n+1];
		check = new int[n+1];
		for(int i=0;i<m;i++) { 
			int a=sc.nextInt();
			int b = sc.nextInt();
			box[a][b]=1;
			box[b][a]=1;
			
		}
		
		bfs(1);
		
		
	}
	
}
