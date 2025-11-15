import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        int[] alpha = new int['Z'-'A' +1];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            int weight = 1;
            for(int j = s.length()-1; j>= 0; j--){
                char ch =  s.charAt(j);
                alpha[ch-'A'] += weight;
                weight *= 10;
            }
        }
        Arrays.sort(alpha);
        int index = 'Z' - 'A';
        int num = 9;
        int sum = 0;
        while(true){
            if(alpha[index] == 0) break;
            sum += alpha[index] * num;
            num--;
            index--;
        }
        System.out.println(sum);
    }
}
