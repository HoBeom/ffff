import java.util.Scanner;

public class week02 {
	public static void main(String[] args) {
		//문제 05:파워포인트
		Scanner input = new Scanner(week02.class.getResourceAsStream("input05.txt"));
		for (int t = 0, T = input.nextInt(), N, M; t < T; t++) {
			int array[] = new int[N = input.nextInt()];
			M = input.nextInt();
			int min = 1, max = 0, mid = 0;
			for (int n = 0; n < N; n++) {
				array[n] = input.nextInt();
				max = array[n] > max ? array[n] : max;
			}
			int P = max /= M / N;
			while (min <= max) {
				mid = min + (max - min) / 2;
				if (isPrint(array, mid, M)) {
					P = P > mid ? mid : P;
					max = mid - 1;
				} else
					min = mid + 1;
			}
			System.out.println(P);
		}
	}

	private static boolean isPrint(int[] array, float mid, int M) {
		int sum = 0;
		for (int i : array)
			sum += (int) Math.ceil(i / mid);
		return sum <= M ? true : false;
	}
}
