import java.util.Scanner;

public class p08 {

	public static void main(String[] args)  {
		Scanner input = new Scanner(p08.class.getResourceAsStream("input08.txt"));
		int T = input.nextInt();
		while (T-- > 0) {
			int N = input.nextInt(), K = input.nextInt();
			String data = input.next().trim();
			System.out.println(Substring(data, K));
		}

	}

	private static int Substring(String data, int k) {
		int visit[] = new int[27];
		int left = 0, right = 0;
		int len = 1, max = 1;
		visit[data.charAt(right++) - 'A']++;
		int N = data.length();
		while (right < N) {
			int ndata = data.charAt(right)-'A';
			if(visit[ndata]<k) {
				visit[ndata]++;
				right++;
				len++;
				max = len > max ?  len:max;
			}else {
				while(visit[ndata]>=k) {
					visit[data.charAt(left++)-'A']--;
					len--;
				}
			}
		}
		return max;
	}

}
