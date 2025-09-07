import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] result = new int[n][3];
        result[0][0] = 1;
        result[0][1] = 1;
        result[0][2] = 1;
        for(int i = 1; i < n; i++){
            result[i][0] = (result[i-1][0] % 9901 + result[i-1][1] % 9901 + result[i-1][2] % 9901) % 9901;
            result[i][1] = (result[i-1][0] % 9901 + result[i-1][2] % 9901) % 9901;
            result[i][2] = (result[i-1][0] % 9901 + result[i-1][1] % 9901) % 9901;
        }
        int sum = 0;
        for(int i = 0; i < 3; i++){
            sum += result[n-1][i] % 9901;
        }
        System.out.println(sum % 9901);
    }
}
