import java.io.*;
import java.util.*;

public class Main 
{
    static int N;
    static int[][] A;            // A[inning][player] : 0~8 플레이어의 타격 결과
    static int maxScore = 0;

    public static void main(String[] args) throws Exception 
  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][9];
    
        for (int i = 0; i < N; i++) 
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
          
            for (int p = 0; p < 9; p++) 
                A[i][p] = Integer.parseInt(st.nextToken());
        }

        // 1번 선수(인덱스 0)는 4번 타자(인덱스 3) 고정
        // 나머지 8명을 순열로 배치
        int[] picked = new int[8];      // 배치할 플레이어 번호들(1~8)
        boolean[] used = new boolean[9]; // used[1..8]만 사용
        permute(0, picked, used);

        System.out.println(maxScore);
    }

    // 깊이 d에 players 1..8 중 하나를 고정
    static void permute(int d, int[] picked, boolean[] used) 
  {
        if (d == 8) 
        {
            // 타순(order) 구성: 4번 타자(인덱스 3)는 0(=1번 선수) 고정
            int[] order = new int[9];
            order[3] = 0;
            int idx = 0;
            for (int pos = 0; pos < 9; pos++) 
            {
                if (pos == 3) // 4번 타자
                  continue;
              
                order[pos] = picked[idx++];
            }
          
            maxScore = Math.max(maxScore, simulate(order));
            return;
        }
        for (int p = 1; p <= 8; p++) 
        {
            if (used[p]) continue;
            used[p] = true;
            picked[d] = p;
            permute(d + 1, picked, used);
            used[p] = false;
        }
    }

    // 주어진 타순으로 N이닝 시뮬레이션
    static int simulate(int[] order) 
  {
        int score = 0;
        int batterIdx = 0; // 이닝이 넘어가도 이어서 타석에 섬

        for (int inning = 0; inning < N; inning++) {
            boolean[] base = new boolean[3]; // 1,2,3루
            int outs = 0;

            while (outs < 3) {
                int player = order[batterIdx];      // 이번 타자(플레이어 인덱스 0~8)
                int res = A[inning][player];        // 이번 이닝에서의 결과(0~4)

                switch (res) 
                {
                    case 0: // 아웃
                        outs++;
                        break;
                    case 1: // 1루타
                        if (base[2]) { score++; base[2] = false; }
                        if (base[1]) { base[2] = true; base[1] = false; }
                        if (base[0]) { base[1] = true; }
                        base[0] = true;
                        break;
                    case 2: // 2루타
                        if (base[2]) { score++; base[2] = false; }
                        if (base[1]) { score++; base[1] = false; }
                        if (base[0]) { base[2] = true; base[0] = false; }
                        base[1] = true;
                        break;
                    case 3: // 3루타
                        if (base[2]) { score++; base[2] = false; }
                        if (base[1]) { score++; base[1] = false; }
                        if (base[0]) { score++; base[0] = false; }
                        base[2] = true;
                        break;
                    case 4: // 홈런
                        if (base[2]) { score++; base[2] = false; }
                        if (base[1]) { score++; base[1] = false; }
                        if (base[0]) { score++; base[0] = false; }
                        score++; // 타자 득점
                        break;
                }
                batterIdx = (batterIdx + 1) % 9;
            }
        }
        return score;
    }
}


/*
본 문제는 본인이 판단하기에 코드 관여율이 50% 미만인듯하여 백준에 제출하지 않았음
*/
