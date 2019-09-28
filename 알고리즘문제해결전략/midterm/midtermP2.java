import java.util.Scanner;

public class midtermP2 {
	// 문제 2번 소스코드입니다. 201430189 전호범
	public static void main(String[] args) {
		new midtermP2();
	}

	int n;
	int[][] board;
	int[][][] visited;

	public midtermP2() {
		Scanner kb = new Scanner(midtermP2.class.getResourceAsStream("input2.txt"));
		int T = kb.nextInt();
		while (0 < T--) {
			n = kb.nextInt();
			board = new int[n][n];
			visited = new int[n][n][4];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					board[i][j] = kb.nextInt();
			}
			if (maze(0, 0)) {
				int i = 0;
				while (!maze(new Position(0, 0, 0), i++))
					visited = new int[n][n][4];// init
				System.out.println(--i);
			} else
				System.out.println(-1);
		}
	}

	boolean maze(Position p, int i) {
		if (p.x < 0 || p.x >= n || p.y < 0 || p.y >= n || board[p.x][p.y] != 0 || visited[p.x][p.y][p.d] != 0)
			return false;
		if (p.x == n - 1 && p.y == n - 1)
			return true;
		visited[p.x][p.y][p.d] = 1;
		for (int turn = 0; turn < 2; turn++) {
			Position q = next_pos(p, turn);
			if (maze(q, i))
				return true;
		}
		if (i > 0)
			if (maze(next_pos(p, -1), i - 1))
				return true;
		return false;
	}

	int offset[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	Position next_pos(Position p, int turn) {
		// -1 for left , 0 for straight, 1 for right turn;
		Position q = new Position();
		q.x = p.x + offset[(p.d + turn + 4) % 4][0];
		q.y = p.y + offset[(p.d + turn + 4) % 4][1];
		q.d = (p.d + turn + 4) % 4;
		return q;
	}

	boolean maze(int x, int y) {
		if (x < 0 || y < 0 || x > n - 1 || y > n - 1 || board[x][y] != 0)
			return false;
		else if (x == n - 1 && y == n - 1)
			return true;
		else {
			board[x][y] = 2;
			boolean temp = maze(x + 1, y);
			temp |= maze(x, y + 1);
			temp |= maze(x - 1, y);
			temp |= maze(x, y - 1);
			board[x][y] = 0;
			return temp;
		}
	}
}

class Position {
	int x, y;
	int d;

	public Position() {
		x = y = d = 0;
	}

	public Position(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}