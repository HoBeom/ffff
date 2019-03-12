package alg13;

import java.util.Scanner;

public class Knapsack {
	int cost[];
	int w[];
	int M[][];

	public static void main(String[] args) {
		new Knapsack();
	}

	Knapsack() {
		System.out.println("Knapsack");
		Scanner kb = new Scanner(System.in);
		int k = kb.nextInt();
		cost = new int[k + 1];
		w = new int[k + 1];
		for (int i = 1; i < cost.length; i++) {
			cost[i] = kb.nextInt();
			w[i] = i;
		}
		M = new int[k + 1][k + 1];
		System.out.println(sack(k));
	}

	private int sack(int n) {
		for (int i = 0; i <= n; i++) {
			M[n][0] = 0;
			M[0][n] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i > j)
					M[i][j] = M[i - 1][j];
				else
					M[i][j] = Math.max(M[i][n - i] + cost[i], M[i - 1][j]);
			}
		}
		return M[n][n];
	}
}
