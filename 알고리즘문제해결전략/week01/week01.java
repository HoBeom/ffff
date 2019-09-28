import java.util.Arrays;
import java.util.Scanner;

public class week01 {

	public static void main(String[] args) {
		long start, end = 0;
		start = System.currentTimeMillis();
		p03();
		end += System.currentTimeMillis() - start;
		System.out.printf("%10.5f \t", end / 1000.0);
	}

	private static void p01() {
		Scanner read = new Scanner(week01.class.getResourceAsStream("input01.txt"));
		int T = read.nextInt();
		for (int t = 0; t < T; t++) {
			int N = read.nextInt();
			int M = read.nextInt();
			int[] array = new int[N];
			for (int i = 0; i < N; i++)
				array[i] = read.nextInt();
			// O(nlogn)
			Arrays.sort(array);
			// O(n-m)
			int min = Integer.MAX_VALUE;
			for (int i = 0; i <= N - M; i++) {
				int temp = -array[i] + array[i + M - 1];
				if (min > temp)
					min = temp;
			}
			System.out.println(min);
		}
		read.close();
	}

	private static void p02() {
		Scanner read = new Scanner(week01.class.getResourceAsStream("input02.txt"));
		int T = read.nextInt();
		for (int t = 0; t < T; t++) {
			int N = read.nextInt();
			int arrival[] = new int[N];
			int departure[] = new int[N];
			for (int i = 0; i < N; i++) {
				arrival[i] = read.nextInt();
				departure[i] = read.nextInt();
			}
			// O(2nlogn)
			// Arrays.sort(arrival);
			Arrays.sort(departure);

			int overlap = 1, platform = 1, i = 1, j = 0;
			while (i < N && j < N) {
				// O(n)
				if (arrival[i] <= departure[j]) {
					i++;
					overlap++;
					if (platform < overlap)
						platform = overlap;
				} else {
					j++;
					overlap--;
				}
			}
			System.out.println(platform);
		}
		read.close();
	}

	private static void p03() {
		Scanner read = new Scanner(week01.class.getResourceAsStream("input03.txt"));
		int T = read.nextInt();
		for (int t = 0; t < T; t++) {
			int N = read.nextInt();
			int[] Bars = new int[N];
			for (int i = 0; i < N; i++)
				Bars[i] = read.nextInt();
			// O(n)
			int i = 0, j = N - 1, max, Lmax = 0, Rmax = 0, water = 0;
			while (i != j) {
				if (Lmax < Bars[i])
					Lmax = Bars[i];
				if (Rmax < Bars[j])
					Rmax = Bars[j];
				if (Lmax > Rmax)
					max = Rmax;
				else
					max = Lmax;
				if (Bars[j] > Bars[i])
					water += max - Bars[i++];
				else
					water += max - Bars[j--];
			}
			System.out.println(water);
		}
		read.close();
	}

}