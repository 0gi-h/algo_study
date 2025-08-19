import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    private static final int[] rx = {0, 0, 1, -1};
    private static final int[] ry = {1, -1, 0, 0};

    private static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static char[][] arr;
    static int[][] checked;
    
    static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));
        checked[0][0] = 1;

        while (!queue.isEmpty()) {
            Node now = queue.remove();

            for(int i = 0; i < 4; i++) {
                int nr = now.r + rx[i];
                int nc = now.c + ry[i];

                if(nr < 0 || nc < 0 || nr >= arr.length || nc >= arr[0].length) {
                    continue;
                }

                if(arr[nr][nc] == '0') {
                    continue;
                }

                if(checked[nr][nc] == 0) {
                    queue.add(new Node(nr, nc));
                    checked[nr][nc] = checked[now.r][now.c] + 1;
                }
            }
        }

        return checked[arr.length - 1][arr[0].length - 1] == 0 ? -1 : checked[arr.length - 1][arr[0].length - 1];
    }
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new char[N][M];
        checked = new int[N][M];
        sc.nextLine();
        
        for(int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        System.out.print(bfs());
        
    }
}
