
//Matrix chain multiplication
public class MCM {
	int[][] m;
	int[][] s;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MCM();
	}

	MCM() {
		int[] array = { 1, 3, 2, 4, 6, 1, 3, 2, 5 };
		System.out.println(multiOrder(array));
		printOrder(0, array.length - 2);
	}

	int multiOrder(int[] p) {
		int n = p.length - 1;
		m = new int[n][n];
		s = new int[n][n];

		for (int r = 1; r < n; r++) {
			for (int i = 0; i < n - r; i++) {
				int j = i + r;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					int temp = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (m[i][j] > temp) {
						m[i][j] = temp;
						s[i][j] = k;
					}
				}
			}
		}
		return m[0][n - 1];
	}

	void printOrder(int i, int j) {
		if (i == j)
			System.out.print("A" + (i + 1));
		else {
			System.out.print("(");
			printOrder(i, s[i][j]);
			printOrder(s[i][j] + 1, j);
			System.out.print(")");
		}
	}
}
