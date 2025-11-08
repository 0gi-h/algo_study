import java.io.*;
import java.util.*;
public class Main {
    static boolean[] water;
    static boolean[][][] v;
    static int A;
    static int B;
    static int C;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.inA));
        StringTokenizer st=new StringTokenizer(br.readLine());
        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        water = new boolean[201];

        v = new  boolean[A+1][B+1][C+1];
        StringBuilder sb = new StringBuilder();
        dfs(0, 0, C);
        for(int i = 0; i <= 200; i++){
            if(water[i]){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
    public static void dfs(int a, int b, int c ){
        if(v[a][b][c]) return;
        if(a == 0){
            water[c] = true;
        }
        v[a][b][c]=true;


        if(a != 0){
            // A -> B
            if(b != B){
                int send = Math.min(B-b, a);
                dfs(a- send, b+send, c);
            }
            // A -> C
            if(c != C){
                int send = Math.min(C-c, a);
                dfs(a - send, b, c + send);
            }
        }


        if(b != 0){
            // B -> A
            if(a != A){
                int send = Math.min(b, A-a);
                dfs(a + send, b - send, c);
            }
            // B -> C
            if(c != C){
                int send = Math.min(b, C-c);
                dfs(a , b-send, c+send);
            }
        }

        if(c != 0){
            // C -> A
            if(a != A){
                int send = Math.min(c, A-a);
                dfs(a + send, b, c - send);
            }
            // C -> B
            if(b != B){
                int send = Math.min(c, B-b);
                dfs(a , b+send, c -send);
            }
        }

    }
}
