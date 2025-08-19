import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q1946 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	
		
		for(int i = 0; i < T; i++) {
			int result = 1;
			
			int N = Integer.parseInt(br.readLine());
			ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
			
			for(int j = 0; j < N; j++) {
				arr.add(new ArrayList<>());
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr.get(j).add(Integer.parseInt(st.nextToken()));
				arr.get(j).add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(arr, (o1, o2) -> o1.get(0).compareTo(o2.get(0)));
			
			int max = arr.get(0).get(1);

			for(int j = 0; j < N; j++) {				
				if(max > arr.get(j).get(1)) {
					max = arr.get(j).get(1);
					result++;
				}
			}

			System.out.println(result);
		}
	}

}
