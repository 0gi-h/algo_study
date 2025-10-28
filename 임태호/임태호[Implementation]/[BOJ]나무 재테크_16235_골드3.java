import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 1},
        {1, 0},
        {1, -1},
        {0, -1},
        {-1, -1}
    };
    public static class Tree {
        int cnt;
        int age;
        Tree next, prev;
        public Tree(){
            cnt = 0;
            age = 0;
            prev = next = this;
        }
        public Tree(int age){
            this.age = age;
            this.cnt = 1;
        }
        public Tree(int age, int cnt){
            this.age = age;
            this.cnt = cnt;
        }
        public void insert(Tree t){
            this.next.prev = t;
            t.next = this.next;
            t.prev = this;
            this.next = t;
        }
        public void delete(Tree t){
            t.prev.next = t.next;
            t.next.prev = t.prev;
            t.prev = null;
            t.next = null;
        }
    }
    static int N;
    static int[][] A;
    static int[][] values;
    static int[][] dead;
    static Tree[][] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        values = new int[N][N];
        dead = new int[N][N];
        trees = new Tree[N][N];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                values[i][j] = 5;
                trees[i][j] = new Tree();
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            trees[row][col].insert(new Tree(age));
        }

        int result = 0;
        for(int i = 0; i < K; i++){
            Spring();
            Summer();
            Fall();
            result = Count();
            if(result == 0) break;
            Winter();
        }
        System.out.println(result);
    }
    public static void Spring(){
        for(int i = 0; i < N; i++){
            for(int j  =0; j < N; j++){
                Tree now = trees[i][j].next;
                while(now.age > 0){
                    Tree del = null;
                    if(now.age * now.cnt <= values[i][j]){
                        values[i][j] -= now.age*now.cnt;
                        now.age++;
                    }else{
                        int poss = values[i][j] / now.age;
                        int imposs = now.cnt - poss;
                        if(poss == 0){
                            dead[i][j] += (now.age/2) * now.cnt;
                            del = now;
                        }else{
                            now.cnt = poss;
                            values[i][j] -= poss*now.age;
                            dead[i][j] += imposs * (now.age/2);
                            now.age++;
                        }
                    }
                    now = now.next;
                    if(del != null){
                        trees[i][j].delete(del);
                    }
                }
            }
        }
    }
    public static void Summer(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                values[i][j] += dead[i][j];
                dead[i][j]= 0;
            }
        }
    }
    public static void Fall(){
        for(int i = 0; i < N; i++){
            for(int j  =0; j < N; j++){
                Tree now = trees[i][j].next;
                while(now.age > 0){
                    if(now.age % 5 != 0){
                        now = now.next;
                        continue;
                    }
                    for(int k = 0; k < 8; k++){
                        int nI = i + d[k][0];
                        int nJ = j + d[k][1];

                        if(nI < 0 || nJ < 0 || nI >= N || nJ >= N) continue;

                        if(trees[nI][nJ].next.age == 1){
                            trees[nI][nJ].next.cnt += now.cnt;
                        }else{
                            trees[nI][nJ].insert(new Tree(1, now.cnt));
                        }
                    }
                    now = now.next;
                }
            }
        }
    }
    public static int Count(){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Tree now = trees[i][j].next;
                while(now.age > 0){
                    count += now.cnt;
                    now = now.next;
                }
            }
        }
        return count;
    }
    public static void Winter(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                values[i][j] += A[i][j];
            }
        }
    }
}
