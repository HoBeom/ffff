package alg13;

import java.util.Scanner;

public class LCSmain {
	String x[], y[];
	int c[][];
	boolean b[];

	public static void main(String[] args) {
		new LCSmain();
	}

	LCSmain() {
		System.out.println("LCS");
		Scanner kb = new Scanner(System.in);
		x = (" " + kb.nextLine()).split("");
		y = (" " + kb.nextLine()).split("");
		c = new int[x.length + 1][y.length + 1];
		System.out.println(lcs(x.length - 1, y.length - 1));
		b = new boolean[x.length + 1];
		printLCS();
	}

	private void printLCS() {
		int i = x.length - 1, j = y.length - 1;
		while (c[i][j] != 0) {
			if (c[i][j] == c[i - 1][j])
				i--;
			else if (c[i][j] == c[i][j - 1])
				j--;
			else {
				b[i] = true;
				i--;
				j--;
			}
		}
		for(i = 0 ; i < b.length;i++) {
			if(b[i])
				System.out.print(x[i]);
		}
	}

	int lcs(int m, int n) {
		for (int i = 0; i <= m; i++)
			c[i][0] = 0;
		for (int j = 0; j <= n; j++)
			c[0][j] = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x[i].equals(y[j]))
					c[i][j] = c[i - 1][j - 1] + 1;
				else
					c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
			}
		}
		return c[m][n];
	}
}
