import java.util.*;
import java.io.*;
class Main {
    //54,432,000
    static int[] sel;
    static boolean[] v;
    static int[][] bat;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        bat = new int[n][9];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 9; j++){
                bat[i][j] = readInt();
            }
        }
        sel = new int[9];
        sel[3] = 0;
        v = new boolean[9];
        v[0] = true;
        permutation(0);
        System.out.println(max);

    }
    public static void permutation(int index){
        if(index == 9){
            calculate();
            return;
        }
        if(index == 3){
            permutation(index+1);
            return;
        }
        for(int i = 0; i < 9; i++){
            if(v[i]) continue;
            v[i] = true;
            sel[index] = i;
            permutation(index + 1);
            v[i] = false;
        }
    }
    public static void calculate(){
        int score = 0;
        int outCount = 0;
        int index = 0;
        int base = 0;
        while(outCount != bat.length *3){
            int now = bat[outCount/3][sel[index]];
            if(now == 0){
                if(++outCount % 3 == 0) base = 0;
            }
            else{
                base <<= now; // 주자 이동
                base = base | (1 << (now-1) ); // 출루
                if(base >= 8){
                    if( (base & (1 << 6)) > 0) score++;
                    if( (base & (1 << 5)) > 0) score++;
                    if( (base & (1 << 4)) > 0) score++;
                    if( (base & (1 << 3)) > 0) score++;
                    base &= 7;
                }
            }
            index = ++index == 9 ? 0 : index;
        }
        max = Math.max(max, score);
    }
    public static int readInt() throws IOException {
        int val;
        int total = 0;
        while((val = System.in.read()) > 32){
            total= total*10 + val- '0';
        }
        return total;
    }
}
