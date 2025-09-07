import java.io.*;
import java.util.*;
public class Solution {
    static class Processor{
        int col, row;
        public Processor(int row, int col){
            this.col = col;
            this.row = row;
        }
    }
    static int[][] d = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    static int n;
    static boolean[][] map;
    static ArrayList<Processor> processors;
    // 프로세서의 최대 크기
    static int max ;
    // 선의 최소 길이
    static int min ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new boolean[n][n];
            processors = new ArrayList<Processor>();
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < n; j++){
                    if(Integer.parseInt(st.nextToken()) == 1){
                        map[i][j] = true;
                        if(i == 0 || j == 0 || i == n-1 || j == n-1) continue;
                        processors.add(new Processor(i, j));
                    }
                }
            }
            max = 0;
            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int index, int total, int count ){
        if(processors.size()-index + count < max) return;

        if(index == processors.size()){
            if(count > max){
                max = count;
                min = total;
            }else if(count == max){
                min = Math.min(min, total);
            }
            return;
        }
        for(int i = 0; i < 4; i++){
            int[] result = check(index, i);
            if(result[0] != -1){
                dfs(index +1, total+ result[1],  count + 1);
            }
            restore(index, i, result[1]);
        }
        dfs(index+1, total, count);
    }
    public static void restore(int index, int dir, int length){
        if(length == -1)
            return;
        Processor cur = processors.get(index);
        int row = cur.row;
        int col = cur.col;
        for(int i = 0; i < length; i++){
            row += d[dir][0];
            col += d[dir][1];
            map[row][col] = false;
        }

    }
    public static int[] check(int count, int dir){
        int result = 0;
        Processor p = processors.get(count);
        if(p.row == n-1 || p.col == n-1 || p.row == 0 || p.col == 0) return new int[]{-1, 0};
        int row = p.row;
        int col = p.col;
        while(true){
            row +=d[dir][0];
            col +=d[dir][1];
            if(row < 0 || col < 0 || row >= n || col >= n) break;
            if(map[row][col]) return new int[]{-1, result};
            map[row][col] = true;
            result++;
        }
        return new int[]{0, result};
    }
}
