import java.util.HashMap;
import java.util.Scanner;

public class p10 {

	public static void main(String[] args)  {
		Scanner input = new Scanner(p10.class.getResourceAsStream("input10.txt"));
		int T = input.nextInt();
		while (T-- > 0) {
			int N = input.nextInt();
			int K = input.nextInt();
			int data[] = new int[N];
			for(int i = 0 ; i < N; i++)
				data[i] = input.nextInt();
			System.out.println(Subsequence2(data,K));
		}

	}

	private static int Subsequence2(int[] data,int K) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int left = 0, right = 0;
		int len = 0, max = 0;
		while (right < data.length) {
			if(!hm.containsKey(data[right])) {
				hm.put(data[right++], 1);
				max = ++len > max ?  len:max;
				continue;
			}
			int value = hm.get(data[right]);
			if(value <K) {
				hm.replace(data[right++], ++value);
				max = ++len > max ? len:max;
			}
			else {
				while(data[left]!=data[right]) {
					int va = hm.get(data[left]);
					hm.replace(data[left++], --va);
					len--;
				}
				hm.replace(data[right++], --value);
			}
		}
		return max;
	}
}