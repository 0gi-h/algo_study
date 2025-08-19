import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static int[] sel;
	static boolean[] v;
	static int max = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][10]; 
		sel = new int[10];
		v = new boolean[10];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sel[4] = 1; // 1번 선수는 4번 타자 고정
		v[1] = true;
		recursive(1);

		System.out.println(max);
	}

	private static void recursive(int idx) {
		if (idx == 10) {
			play();
			return;
		}

		if (idx == 4) {
			recursive(idx + 1);
			return;
		}

		for (int i = 2; i <= 9; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[idx] = i;
				recursive(idx + 1);
				v[i] = false;
			}
		}
	}

	private static void play() {
		int score = 0;
		int n = arr.length - 1; // 이닝 수
		int batterIdx = 1; // sel의 인덱스 (1~9)

		for (int inning = 1; inning <= n; inning++) {
			int out = 0;
			int base = 0; // 비트마스크로 베이스 상태 관리

			while (out < 3) {
				int player = sel[batterIdx];
				int result = arr[inning][player];

				if (result == 0) {
					out++;
				} else {
					// 주자 이동
					base <<= result;
					base |= (1 << (result - 1)); // 타자 진루

					// 홈 도달 주자 점수 처리
					for (int b = 3; b < 7; b++) {
						if ((base & (1 << b)) != 0) {
							score++;
						}
					}

					// 1~3루 상태 유지
					base &= 0b111;
				}

				batterIdx++;
				if (batterIdx == 10) batterIdx = 1;
			}
		}

		max = Math.max(max, score);
	}
}
