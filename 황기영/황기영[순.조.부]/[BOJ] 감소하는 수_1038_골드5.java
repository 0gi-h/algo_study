import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Long> list = new ArrayList<>();
    static int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};   
    static int[] sel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int r = 1; r <= 10; r++) {
            sel = new int[r];
            combination(0, 0, r);
        }

        Collections.sort(list);

        if (n >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n));
        }
    }

    static void combination(int idx, int k, int r) {
        if (k == r) {
            StringBuilder sb = new StringBuilder();
            for (int i = r - 1; i >= 0; i--) {
                sb.append(sel[i]);
            }
            list.add(Long.parseLong(sb.toString()));
            return;
        }

        if (idx == 10) return;

        sel[k] = num[idx];
        combination(idx + 1, k + 1, r);
        combination(idx + 1, k, r);
    }
}
