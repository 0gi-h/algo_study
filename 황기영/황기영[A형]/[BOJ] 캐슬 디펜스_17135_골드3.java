import java.io.*;
import java.util.*;
 
public class Q17135 {
   static int N,M,D;
   static int[] picked = new int[3];
   static int[][] map;
   static int MAX = 0;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      D = Integer.parseInt(st.nextToken());
      map = new int[N][M];
      for(int i=0;i<N;i++) {
         st = new StringTokenizer(br.readLine());
         for(int j=0;j<M;j++){
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      pick(0,0);
      System.out.println(MAX);
   }
 
   public static void pick(int idx, int start){
      if(idx==3){
         MAX = Math.max(MAX,game());
         return;
      }
      if(start >= M){
          return; // 더 이상 선택할 수 있는 인덱스가 없음
      }
      
      // 현재 start 인덱스를 선택하는 경우
      picked[idx] = start;
      pick(idx + 1, start + 1);
      
      // 현재 start 인덱스를 선택하지 않는 경우
      pick(idx, start + 1);
   }
 
	 //상태를 저장할 배열 status
	 //궁수를 한 줄씩 올리는 방식(line)
	 //거리 1~D까지 반복문으로 돌면서 attack 호출
	 //잡으면 반환값을 count에 추가
   public static int game(){
      int count = 0;
      int[][] status = new int[N][M];
 
      for(int line = N;line>0;line--){
         for(int pick:picked){
            for(int d=1;d<=D;d++){
               int cnt = attack(status,d,line,pick);
               if(cnt<0) continue;
               count+=cnt;
               break;
            }
         }
      }
 
      return count;
   }
 
	 //pick은 궁수가 배치된 열, +- d의 배열만 탐색
	 //적의 위치(nn, nm), nn은 d-(Math.abs(nm - pick), 여기가 핵심
	 //status배열이 0이면 아직 처치하지 않은 적, 현재 궁수의 행(line)을 저장
	 //현재 line과 status배열의 값이 같다면 다른 궁수가 쏜 적
   public static int attack(int[][] status, int d, int line, int pick){
      int nn;
      for(int nm=pick-d;nm<=pick+d;nm++){
         nn = line-(d-Math.abs(nm-pick));
         if(nn<0 || nn >= line || nm <0 || nm >= M) continue;
         if(map[nn][nm]==0) continue;
         if(status[nn][nm] == 0){
            status[nn][nm] = line;
            return 1;
         }else if(status[nn][nm] == line) return 0;
      }
 
      return -1;
   }
 
}
