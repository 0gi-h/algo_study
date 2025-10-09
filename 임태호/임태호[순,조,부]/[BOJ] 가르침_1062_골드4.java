import java.io.*;
import java.util.*;
public class Main {
    static boolean[] v = new boolean[26];
    static int[] words;
    static int k;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());
        words = new int[n];
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++){
                words[i] |= (1 << s.charAt(j) - 'a');
            }
        }
        comb(0, 0, 0);
        System.out.println(max);
    }
    static void comb(int count, int index, int value){
        if(count == k){
            max = Math.max(max, check(value));
        }
        if(index == 26) return;
        v[index] = true;
        comb(count+1, index+1, value | (1 << index));
        v[index] = false;
        if(index == 'a'-'a' || index == 'c' - 'a' || index == 't'-'a' || index == 'i' -'a' || index == 'n' -'a') return;
        comb(count, index+1, value);
    }
    static int check(int value){
        int count = 0;
        boolean c;
        for(int i = 0; i < words.length; i++){
            if((words[i] & value) == words[i]) count++;
        }
        return count;
    }
}
