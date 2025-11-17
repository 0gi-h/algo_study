import java.io.*;
import java.util.*;

public class Main {    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    static int N, trueCnt, max;
    static boolean[] alphabetArr;
    static String[] wordArr;
    
    static int[] numArr;
    static int[] selArr;
    static boolean[] checkArr;
    static int[] alphabetToIndex;
    
    public static void main(String[] args) throws IOException 
    {
        N = Integer.parseInt(br.readLine());
        alphabetArr = new boolean[26];
        wordArr = new String[N];
        alphabetToIndex = new int[26];
        
        trueCnt = 0;
        max = 0;
        
        for(int i = 0; i < N; i++)
        {
            wordArr[i] = br.readLine();
            
            for(int j = 0; j < wordArr[i].length(); j++)
            {
                int idx = wordArr[i].charAt(j) - 'A';
                if(!alphabetArr[idx])
                    alphabetArr[idx] = true;
            }
        }
        
        int index = 0;
        for(int i = 0; i < 26; i++)
        {
            if(alphabetArr[i])
            {
                alphabetToIndex[i] = index;
                index++;
            }
        }
        trueCnt = index;
        
        numArr = new int[trueCnt];
        for(int i = 0; i < trueCnt; i++)
            numArr[i] = 9 - i; 
        
        selArr = new int[trueCnt];
        checkArr = new boolean[trueCnt];
        
        permutation(0);
        
        System.out.println(max);
    }

    private static void permutation(int k) 
    {
        if(k == trueCnt)
        {
            int sum = 0;
            
            for(String word : wordArr)
            {
                int num = 0;
                for(int i = 0; i < word.length(); i++)
                {
                    int charIdx = word.charAt(i) - 'A';
                    int arrayIdx = alphabetToIndex[charIdx];
                    num = num * 10 + selArr[arrayIdx];
                }
                sum += num;
            }
            
            max = Math.max(max, sum);
            return;
        }
        
        for(int i = 0; i < numArr.length; i++)
        {
            if(!checkArr[i])
            {
                checkArr[i] = true;
                selArr[k] = numArr[i];
                permutation(k + 1);
                checkArr[i] = false;
            }
        }
    }
}
