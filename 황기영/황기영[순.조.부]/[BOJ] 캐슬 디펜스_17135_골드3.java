import java.io.*;
import java.util.*;
 
public class Main {
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
