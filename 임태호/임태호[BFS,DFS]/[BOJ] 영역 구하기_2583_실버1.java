import java.io.*;
import java.util.*;

public class Main {
    public static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] v = new boolean[n][m];
        int count = Integer.parseInt(st.nextToken());
        for(int i = 0; i < count ; i++){
            st=new StringTokenizer(br.readLine());
            int startCol = Integer.parseInt(st.nextToken());
            int startRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            for(int j = startRow; j < endRow; j++){
                for(int k = startCol; k < endCol; k++){
                    v[j][k] = true;
                }
            }
        }
        int areas = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!v[i][j]){
                    areas++;
                    int points = 0;
                    q.offer(new int[]{i, j});
                    v[i][j] = true;
                    while(!q.isEmpty()){
                        int[] now =  q.poll();
                        points++;
                        for(int[] k : d){
                            int nRow = now[0] + k[0];
                            int nCol = now[1] + k[1];
                            if(nRow < 0 || nRow >= n || nCol < 0 || nCol >= m || v[nRow][nCol]) continue;
                            v[nRow][nCol] = true;
                            q.offer(new int[]{nRow, nCol});
                        }
                    }
                    arr.add(points);
                }
            }
        }
        Collections.sort(arr);
        sb.append(areas).append("\n");
        for(int i = 0; i < arr.size(); i++){
            sb.append(arr.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
