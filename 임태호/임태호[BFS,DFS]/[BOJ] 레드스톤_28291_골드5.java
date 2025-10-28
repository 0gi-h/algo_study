import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d ={
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int N =  Integer.parseInt(br.readLine());

        int[][][] map = new int[2][H][W];
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> lamps = new ArrayList<>();
        //9번째 인덱스가 d, b, l
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String block =  st.nextToken();
            int col =  Integer.parseInt(st.nextToken());
            int row =  Integer.parseInt(st.nextToken());

            switch(block.charAt(9)){
                case 'd' :
                    map[0][row][col] = 1;
                    break;
                case 'b' :
                    map[0][row][col] = 2;
                    map[1][row][col] = 15;
                    q.offer(new int[]{row, col});
                    break;
                case 'l' :
                    map[0][row][col] = 3;
                    lamps.add(new int[]{row, col});
                    break;
            }
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int row =  cur[0];
            int col =  cur[1];
            int value = map[1][row][col];
            if(value == 1) continue;
            int type = map[0][row][col];

            switch(type){
                // 레드스톤 블록
                case 2 :
                    for(int i = 0; i < 4; i++){
                        int nRow =  row + d[i][0];
                        int nCol = col + d[i][1];
                        if(nRow >= 0 && nRow < H && nCol >= 0 && nCol < W ){
                            // 다음 위치가 가루일 떄
                            if(map[0][nRow][nCol] == 1) {
                                map[1][nRow][nCol] = 15;
                                q.offer(new int[]{nRow, nCol});
                            //최종 목적지 일 때
                            }else if(map[0][nRow][nCol] == 3){
                                map[1][nRow][nCol] = 15;
                            }
                        }
                    }
                    break;
                // 레드 스톤 가루일 때
                case 1 :
                    for (int i = 0; i < 4; i++) {
                        int nRow = row + d[i][0];
                        int nCol = col + d[i][1];
                        if (nRow >= 0 && nRow < H && nCol >= 0 && nCol < W) {
                            // 다음 위치가 가루 일 때만 이동이 가능하다
                            if (map[0][nRow][nCol] == 1) {
                                if(value - 1 > map[1][nRow][nCol]){
                                    q.offer(new int[]{nRow, nCol});
                                    map[1][nRow][nCol] = value-1;
                                }
                            } else if (map[0][nRow][nCol] == 3) {
                                map[1][nRow][nCol] = value -1;
                            }
                        }
                    }
            }
        }
        for(int[] lamp : lamps){
            if(map[1][lamp[0]][lamp[1]] == 0){
                System.out.println("failed");
                return;
            }
        }
        System.out.println("success");

    }
}
