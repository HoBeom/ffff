import java.util.Scanner;

public class midtermP1 {
	//���� 1�� �ҽ��ڵ��Դϴ�. 201430189 ��ȣ��
	public static void main(String[] args) {
		Scanner kb = new Scanner(midtermP1.class.getResourceAsStream("input1.txt"));
		int T = kb.nextInt();
		while (0 < T--) {
			//�Է� ������ �迭�� ũ�⸦ �̸� �ֱ⶧���� �迭�� ũ�⸦ �ʿ��Ѹ�ŭ�� �Ҵ��Ͽ����ϴ�.
			int[][] edge = new int[kb.nextInt()][];
			for (int i = 0; i < edge.length; i++) {
				edge[i] = new int[kb.nextInt()];
				for (int j = 0; j < edge[i].length; j++) {
					edge[i][j] = kb.nextInt();
				}
			}
			//������ ������ �ι��̻� ������ �ʴ´�.
			boolean path[] = new boolean[edge.length];
			//������� start, �������� end, ����� ���� L
			System.out.println(countPath(edge,path, kb.nextInt(), kb.nextInt(), kb.nextInt(), 0));
		}
	}
	
	private static int countPath(int[][] edge,boolean path[], int start, int end, int L, int degree) {
		if (L < degree)
			return 0;
		if (start == end)
			return 1;
		if (path[start])
			return 0;
		else
			path[start]=true;
		//���� ������ �湮���� üũ�� ���� �������� end���� �����ִ� ����� ������ ����� ����
		int sum = 0;
		for (int i = 0; i < edge[start].length; i++)
			sum += countPath(edge,path, edge[start][i], end, L, degree + 1);
		path[start]=false;
		return sum;
	}

}
