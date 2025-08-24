import java.io.*;
import java.util.*;
public class Main {
    static int[] people;
    static ArrayList<Integer>[] arr;
    static int[] root;
    static int n;
    static boolean[] v;
    static int aCount;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n  =Integer.parseInt(br.readLine());
        people = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }
        arr = new ArrayList[n];
        v = new boolean[n];
        for(int i = 0; i< n ; i++){
            arr[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j =0; j < count ; j++){
                arr[i].add(Integer.parseInt(st.nextToken()) -1);
            }
        }
        aCount = 0;
        combination(0);
        System.out.println(min==Integer.MAX_VALUE ? -1 : min);

    }

    public static void combination(int index){
        if(index == n){
            if(aCount == n || aCount == 0) return;
            check();
            return;
        }

        v[index] = true;
        aCount++;
        combination(index+1);
        aCount--;
        v[index] = false;

        combination(index+1);
    }
    public static void check(){
        root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }


        for(int i = 0; i < n; i++){
            for(int j = 0; j < arr[i].size(); j++){
                if(v[i] != v[arr[i].get(j)]) continue;
                union(i, arr[i].get(j));
            }
        }
        int[] sum = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            root[i] = find(i);
            set.add(root[i]);
            sum[root[i]] += people[i];
        }
        if(set.size() != 2) return;
        boolean first = false;
        int result = 0;
        for(int i = 0; i < n; i++){
            if(!first && sum[i] != 0){
                result+= sum[i];
                first = true;
            }
            else if(first && sum[i] != 0){
                result = Math.abs(result - sum[i]);
            }
        }
        min = Math.min(min, result);

    }
    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);


        if (rootA == rootB) return;
        if (rootA > rootB) root[rootA] = rootB;
        else root[rootB] = rootA;
    }
    public static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }
}
