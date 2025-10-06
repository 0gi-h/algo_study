import java.io.*;
import java.util.*;

public class Main {
	 
    static int n, k;
    static int max = 0;
    static boolean[] v;
    static String[] word;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        word = new String[n];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            str = str.substring(4, str.length()-3);
            word[i] = str;
        }
        
        if(k < 5) { 
            System.out.println("0");
            return;
        } else if(k == 26) { 
            System.out.println(n);
            return;
        }
        
        v = new boolean[26]; 
        v['a' - 'a'] = true;
        v['c' - 'a'] = true;
        v['i' - 'a'] = true;
        v['n' - 'a'] = true;
        v['t' - 'a'] = true;
        
        recursive(0, 0);
        System.out.println(max);
    }
    
    public static void recursive(int idx, int len) {
        if(len == k - 5) {
            int count = 0;
            for(int i = 0; i < n; i++) { 
                boolean read = true;
                for(int j = 0; j < word[i].length(); j++) {
                    if(!v[word[i].charAt(j) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if(read) count++;
            }
            max = Math.max(max, count);
            return;
        }
        
        for(int i = idx; i < 26; i++) {
            if(v[i] == false) {
                v[i] = true;
                recursive(i, len + 1);
                v[i] = false;
            }
        }
    }
}
