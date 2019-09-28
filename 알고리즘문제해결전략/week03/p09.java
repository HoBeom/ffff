import java.util.HashSet;
import java.util.Scanner;

public class p09 {

	public static void main(String[] args)  {
		Scanner input = new Scanner(p09.class.getResourceAsStream("input09.txt"));
		int T = input.nextInt();
		while (T-- > 0) {
			int N = input.nextInt();
			int data[] = new int[N];
			for(int i = 0 ; i < N; i++){
				data[i] = input.nextInt();
			}
			System.out.println(Subsequence(data));
		}

	}

	private static int Subsequence(int[] data) {
		HashSet<Integer> hs = new HashSet<Integer>();
		int left = 0, right = 0;
		int len = 0, max = 0;
		while (right < data.length) {
			if(!hs.contains(data[right])) {
				hs.add(data[right++]);
				len++;
				max = len > max ?  len:max;
			}else {
				while(hs.contains(data[right])) {
					hs.remove(data[left++]);
					len--;
				}
			}
		}
		return max;
	}
}
