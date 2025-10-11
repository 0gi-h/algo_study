import java.util.*;
import java.io.*;
public class Main {
    static ArrayList<int[]> homes;
    static ArrayList<int[]> chickens;
    static int min;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        homes =new ArrayList<>();
        chickens =new ArrayList<>();

        min = Integer.MAX_VALUE;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int c =  Integer.parseInt(st.nextToken());
                if( c == 2 ){
                    chickens.add(new int[] {i,j});
                }else if( c == 1 ){
                    homes.add(new int[] {i,j});
                }
            }
        }
        dist = new int[homes.size()][chickens.size()];

        for(int i = 0; i < homes.size(); i++){
            int[] nhome = homes.get(i);
            for(int j = 0; j < chickens.size(); j++){
                int[] nchicken = chickens.get(j);
                dist[i][j] = Math.abs(nhome[0] - nchicken[0]) + Math.abs(nhome[1] - nchicken[1]);
            }
        }

        for(int i = 0; i < (1 << chickens.size()); i++){
            if(bitCount(i) == m){
                int sumDist = calculate(i);
                min = Math.min(min, sumDist);
            }

        }

        System.out.println(min);


    }
    public static int calculate(int i){
        int result = 0;

        for(int j = 0; j < homes.size(); j++){
            int mV = Integer.MAX_VALUE;
            for(int k = 0; k < chickens.size(); k++){
                if((i & (1 << k)) == 0 || mV < dist[j][k]) continue;
                mV = dist[j][k];
            }
            result += mV;
        }
        return result;

    }
    public static int bitCount(int n){
        int count = 0;

        /*
        1101에서 1의 개수를 파악하는 방법
        1100

        1100
        1011

        1000
        0111

        0000
         */
        while( n != 0){
            n &= (n-1);
            count++;
        }
        return count;
    }
}
