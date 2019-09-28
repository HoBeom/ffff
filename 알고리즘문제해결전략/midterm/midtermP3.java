import java.util.Scanner;

public class midtermP3 {
	//���� 3�� �ҽ��ڵ��Դϴ�. 201430189 ��ȣ��
	int C;
	public static void main(String[] args) {
		new midtermP3();
	}

	public midtermP3() {
		Scanner kb = new Scanner(midtermP3.class.getResourceAsStream("input3.txt"));
		int T = kb.nextInt();
		while (0 < T--) {
			// M = ECM case < 20
			int M = kb.nextInt();
			//3�����迭 �˼��մϴ�.
			int ECM[][][] = new int[M][][];
			for (int i = 0; i < M; i++) {
				// [options][cost/saving]
				ECM[i] = new int[kb.nextInt()][2];
				for (int j = 0; j < ECM[i].length; j++) {
					ECM[i][j][0] = kb.nextInt();// cost
					ECM[i][j][1] = kb.nextInt();// saving
				}
			}
			// C is maximum Cost
			C = kb.nextInt();
			// savesum is my recent value
			int savesum = 0;
			// making powerset
			// max is 2^M, powerset cases
			int max = 1 << M;
			for (int i = 1; i < max; ++i) {
				//�ɼ��� �����ϴ� ����Ǽ��� set�迭�� ����ϴ�.
				//i�� ��Ʈ�� ī��Ʈ�Ͽ� �迭�� ũ�⸦ ���ϰ� ��Ʈ�� �¼��� ������ �ɼ��� �ε����� �˴ϴ�.
				int[] set = new int[Integer.bitCount(i)];
				for (int j = 0, b = i, k = 0; j < M; ++j, b >>= 1)
					if ((b & 1) != 0)
						set[k++] = j;
				//set�� maximal�� �޾� ���� maximum�� ���մϴ�.
				int maximal = selectoption(ECM, set, 0, 0);
				if (savesum < maximal)
					savesum = maximal;
			}
			//������ ����� ã�� ���Ͽ����ϴ�..
			//O(2^M)�̹Ƿ� ������ �����ɸ��ϴ�.
			//C������ �ɼ��� ����� ������쿡�� ����� �Ұ����������� �ӵ��� �����ϴ�.
			System.out.println(savesum);
		}
	}

	private int selectoption(int[][][] ecm, int[] set, int degree, int cost) {
		if (set.length == degree)
			return 0;
		int select = set[degree];
		//set�� maximal�� ���Ͽ� ��ȯ�մϴ�.
		int maximal = 0;
		int temp = 0;
		for (int i = 0; i < ecm[select].length; i++) {
			if (ecm[select][i][0] + cost < C)
				temp = ecm[select][i][1] + selectoption(ecm, set, degree + 1, ecm[select][i][0]+cost);
			if (maximal < temp)
				maximal = temp;
		}
		return maximal;
	}
}
