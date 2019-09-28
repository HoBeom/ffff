import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class p11 {
	static int[][] matrix = new int[9][9];
	//백준 2580번  스도쿠
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				matrix[i][j] = input.nextInt();
		}
		input.close();
		in.close();
		startSudoku();
	}

	private static void startSudoku() throws IOException {
		int x = -1, y = -1;
		findzero: for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (matrix[i][j] == 0) {
					x = i;
					y = j;
					break findzero;
				}
			}
		}
		if (x == -1) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					bw.write(matrix[i][j] + " ");
				}
				bw.write("\n");
			}
			bw.flush();
			bw.close();
			return;
		}
		for (int select = 1; select < 10; select++) {
			if (checkNumber(x, y, select)) {
				matrix[x][y] = select;
				startSudoku();
				matrix[x][y] = 0;
			}
		}
	}

	private static boolean checkNumber(int x, int y, int select) {
		for (int i = 0; i < 9; i++) {
			if (matrix[x][i] == select || matrix[i][y] == select)
				return false;
		}
		int xran = (x / 3) * 3 + 3, yran = (y / 3) * 3 + 3;
		for (int i = x / 3 * 3; i < xran; i++) {
			for (int j = y / 3 * 3; j < yran; j++)
				if (matrix[i][j] == select)
					return false;
		}
		return true;
	}
}
