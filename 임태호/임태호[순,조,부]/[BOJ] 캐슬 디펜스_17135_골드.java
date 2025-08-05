import java.io.*;
import java.util.*;
public class Main{
	//map
	static boolean[][] map;
	static int[][] attack;
	static int enemy = 0;
	static int max = 0;
	//comb
	static int[] sel;
	static boolean[] v;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int pow = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		sel = new int[3];
		v = new boolean[m];
		for(int i = 0; i < n ; i++
		){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++){
				if(Integer.parseInt(st.nextToken()) == 1) {
					map[i][j] = true;
					enemy++;
				}
			}
		}
		attack = new int[pow * pow][2];
		int index = 0;
		for(int i = 1; i <= pow; i++){
			for(int j = -1* (pow - i); j <= pow-i; j++){
				attack[index][0] = i;
				attack[index++][1] = j;
			}
		}
		Arrays.sort(attack, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int dist1 = Math.abs(o1[0]) + Math.abs(o1[1]);
				int dist2 = Math.abs(o2[0]) + Math.abs(o2[1]);
				if(dist1 != dist2) return dist1 - dist2;
				else return o1[1] - o2[1];
			}
		});
		comb(0,0);
		System.out.println(max);

	}
	public static void comb(int idx, int num){
		if(idx == 3){
			gameStart();
			return;
		}
		if(num == map[0].length) return;


		sel[idx] = num;
		comb(idx+1 , num+1);
		comb(idx, num+1);
	}
	public static void gameStart(){
		boolean[][] temp = new boolean[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			System.arraycopy(map[i], 0, temp[i], 0, map[0].length);
		}
		int kp = 0;
		int sCol = temp.length;
		// 시간
		for(int i = 0; i < temp.length; i++){
			Set<List<Integer>> set = new HashSet<>();
			//궁수
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < attack.length; k++){
					int nextCol = sCol - attack[k][0];
					int nextRow = sel[j] + attack[k][1];
					if(check(nextCol,nextRow)) continue;
					if(temp[nextCol][nextRow]){
						set.add(Arrays.asList(nextCol, nextRow));
						break;
					}
				}
			}
			for(List<Integer> l : set){
				kp++;
				temp[l.get(0)][l.get(1)]= false;
			}
			sCol--;
		}
		max = Math.max(kp,max);
	}
	public static boolean check(int col, int row){
		if(col >= map.length || row >= map[0].length || col < 0 || row < 0) return true;
		return false;
	}
}
