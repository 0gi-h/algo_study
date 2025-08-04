import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        int n =read();
        boolean[] visited = new boolean[n+1];
        int e = read();
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for(int i = 0; i <=n; i++){
            arr.add(new ArrayList<>());
        }
        for(int i = 0; i < e; i++){
            int start= read();
            int end = read();
            arr.get(start).add(end);
            arr.get(end).add(start);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int count = 0;
        while(queue.size() > 0){
            int now = queue.poll();
            for(int i = 0; i < arr.get(now).size(); i++){
                int next = arr.get(now).get(i);
                if(!visited[next]){
                    queue.offer(next);
                    visited[next] = true;
                    count++;
                }
            }
        }
        System.out.print(count);
                
    }
    public static int read() throws IOException{
        int val;
        int total = 0;
        while((val = System.in.read()) > 32 ){
            total = 10*total + val -'0';
        }
        return total;
    }
}
