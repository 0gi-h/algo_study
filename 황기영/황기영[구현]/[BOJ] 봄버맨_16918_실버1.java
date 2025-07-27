import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		String[][] arr = new String[R][C];
		for(int i = 0; i < R; i++) {
			arr[i] = br.readLine().split("");
		}
		
		int dr[] = {1, 0, -1, 0};
		int dc[] = {0, -1, 0, 1};
	
		
		for(int n = 1; n <= N; n++) {

			if(n % 2 == 0) {
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(arr[i][j].equals(".")) {
							arr[i][j] = "!";
						}
					}
				}
			}
			
			else if(n % 4 == 3) {
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(arr[i][j].equals("O")) {
							
							for(int k = 0; k < 4; k++) {
								int nr = i + dr[k];
								int nc = j + dc[k];
								
								if(nr >= 0 && nr < R && nc >=0 && nc < C && !arr[nr][nc].equals("O")) {
									arr[nr][nc] = ".";
								}
							}
						}
					}
				}
				
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(arr[i][j].equals("O")) {
							arr[i][j] = ".";
						}
						else if(arr[i][j].equals("!")) {
							arr[i][j] = "O";
						}
					}
				}
			}
			else if(n > 1 && n % 4 == 1) {
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(arr[i][j].equals("O")) {
							
							for(int k = 0; k < 4; k++) {
								int nr = i + dr[k];
								int nc = j + dc[k];
								
								if(nr >= 0 && nr < R && nc >=0 && nc < C && !arr[nr][nc].equals("O")) {
									arr[nr][nc] = ".";
								}
							}
						}
					}
				}
				
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(arr[i][j].equals("O")) {
							arr[i][j] = ".";
						}
						else if(arr[i][j].equals("!")) {
							arr[i][j] = "O";
						}
					}
				}
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j].equals("!")) {
					System.out.print("O");
				}
				else {
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
	}

}
