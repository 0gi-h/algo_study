import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String str;
	static String[] strs;
	static String[] sel;
	static int n;
	static int num;
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		while((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input, " ");
			str = st.nextToken();
			strs = str.split("");
			sel = new String[strs.length];
			n = Integer.parseInt(st.nextToken());
			num = n;
			boolean[] v = new boolean[strs.length];
			int max = 1;
			
			for(int i = str.length(); i > 0; i--) {
				max *= i;
			}
			
			if(n > max) {
				System.out.println(str + " " + num + " = No permutation");
				continue;
			}
			
			recursive(v, 0);
			
			
			
		}
			
	}
	
	public static void recursive(boolean[] v, int idx) {
		if(idx == strs.length) {
			if(n == 1) {
				System.out.println(str + " " + num +" = " + Main.toString(sel));
			}
			--n;
			return;
		}
		
		for(int i = 0; i < strs.length; i++) {
			if(v[i] == false) {
				v[i] = true;
				sel[idx] = strs[i];
				recursive(v, idx + 1);
				v[i] = false;
				if(n == 0) {
					break;
				}
			}
		}
	}

	public static String toString(String[] sel2) {
        if (sel2 == null)
            return "null";
        int iMax = sel2.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(sel2[i]);
            if (i == iMax)
                return b.toString();
        }
    }

}
