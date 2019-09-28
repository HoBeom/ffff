import java.util.Scanner;

public class midtermP3 {
	//문제 3번 소스코드입니다. 201430189 전호범
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
			//3차원배열 죄송합니다.
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
				//옵션을 선택하는 경우의수를 set배열로 만듭니다.
				//i의 비트를 카운트하여 배열의 크기를 정하고 비트의 승수가 선택할 옵션의 인덱스가 됩니다.
				int[] set = new int[Integer.bitCount(i)];
				for (int j = 0, b = i, k = 0; j < M; ++j, b >>= 1)
					if ((b & 1) != 0)
						set[k++] = j;
				//set의 maximal을 받아 현재 maximum과 비교합니다.
				int maximal = selectoption(ECM, set, 0, 0);
				if (savesum < maximal)
					savesum = maximal;
			}
			//최적의 방법을 찾지 못하였습니다..
			//O(2^M)이므로 굉장히 오래걸립니다.
			//C에비해 옵션의 비용이 낮은경우에는 계산이 불가능할정도로 속도가 느립니다.
			System.out.println(savesum);
		}
	}

	private int selectoption(int[][][] ecm, int[] set, int degree, int cost) {
		if (set.length == degree)
			return 0;
		int select = set[degree];
		//set의 maximal을 구하여 반환합니다.
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
