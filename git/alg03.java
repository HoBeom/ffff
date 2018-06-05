package alg03;

import java.util.Scanner;

public class alg03 {
	int[][] data;
	int N;
	int K;

	public static void main(String[] args) {
		new alg03();
		new NoBreak();
	}

	alg03() {
		read();
	}

	void read() {
		try {
			Scanner read = new Scanner(alg03.class.getResourceAsStream("input1.txt"));
			int T = read.nextInt();
			while (T-- > 0) {
				N = read.nextInt();
				data = new int[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++)
						data[i][j] = read.nextInt();
				}
				K = read.nextInt();
				System.out.println(maze(0,0,0));
			}
			read.close();
		} catch (Exception e) {
			System.exit(9);
		}
	}

	int maze(int x, int y, int dist) {
		//System.out.println(x + "," + y);
		if (x < 0 || y < 0 || x > N - 1 || y > N - 1 || data[x][y] != 0) 
			return 0;
		else if (dist > K)
			return 0;
		else if (x == N - 1 && y == N - 1)
			return 1;
		else {
			data[x][y] = 2;
			int count = maze(x + 1, y, dist + 1);
			count += maze(x, y + 1, dist + 1);
			count += maze(x - 1, y, dist + 1);
			count += maze(x, y - 1, dist + 1);
			data[x][y] = 0;
			return count;
		}
	}
}
