import java.io.*;
import java.util.*;
public class Main{
    public static int max = Integer.MIN_VALUE;
    public static int n;
    public static String s;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = br.readLine();
        if(n == 1) max = Integer.parseInt(s);
        else if(n == 3) max = calculate(postFix(s));
        else dfs(0, "");
        System.out.print(max);
    }
    public static void dfs(int idx, String str) {
        if(idx >= n){
            int result = calculate(postFix(str));
            max = Math.max(max,result);
            return;
        }
        if(idx + 3 == n){
            dfs(idx + 3, str + s.substring(idx));
            dfs(idx + 3, str + "(" + s.substring(idx , idx+3) +")");
        }else if(idx + 3 > n){
            dfs(idx + 3, str + s.substring(idx));
        }else{
            dfs(idx + 4, str + "(" + s.substring(idx, idx+3) + ")" + s.charAt(idx+3));
            dfs(idx + 2, str + s.substring(idx, idx + 2));
        }   
    }
    public static ArrayList<Character> postFix(String s){
        ArrayList<Character> list = new ArrayList<>();
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char now = s.charAt(i);
            if(priorityOper(now) == 0){
                if(now == '('){
                    st.push(now);
                }else if(now == ')'){
                    while (!st.isEmpty() && st.peek() != '(') {
                        list.add(st.pop());
                    }
                    st.pop();
                }else{
                    list.add(now);
                }
            }else{
                if(st.size() == 0) st.push(now);
                else{
                    while (!st.isEmpty() && priorityOper(st.peek()) >= priorityOper(now)) {
                        list.add(st.pop());
                    }
                    st.push(now);
                }
            }

        }
        while(st.size() != 0){
            list.add(st.pop());
        }
        return list;
    }
    public static int calculate(ArrayList<Character> arr){
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.size(); i++){
            char now = arr.get(i);
            if(priorityOper(arr.get(i)) == 0) st.push(now - '0');
            else{
                int num2 = st.pop();
                int num1 = st.pop();
                if(now == '*'){
                    st.push(num1 * num2);
                }else if(now == '-'){
                    st.push(num1 - num2);
                }else{
                    st.push(num1 + num2);
                }
            }
        }
        return st.pop();
    }
    public static int priorityOper(char ch){
        return ch == '*' ? 2 : ch == '+' ? 2 : ch == '-' ? 2 : 0;
    }
}
