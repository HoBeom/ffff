
import java.util.Scanner;

public class p10 {
	public static void main(String[] args) {
		/*
		 * ���� 10: �Է����� �� ���� ���� N�� K�� ���� �� 1���� N������ ������ �߿��� ���� �ٸ� K���� ������ ���� �ϴ� ��� ��츦
		 * �����Ͽ� �� �ٿ� �ϳ��� ����ϴ� ���α׷��� �ۼ��϶�.  �� ��µǴ� ������ �޶�����
		 * �����ϰ� N�� 20�����̰� K?N�̴�. �Է��� Ű����� ���� �޴� ��.
		 */
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int[] printlist = new int[kb.nextInt()];
		printCombi(N,printlist,0,1);
	}

	private static void printCombi(int n, int[] printlist, int listindex, int selectNumber) {
		if(listindex==printlist.length) {
			for(int i = 0 ; i < printlist.length ; i++)
				System.out.print(printlist[i]+" ");
			System.out.println();
		}else if(selectNumber>n) {
			return;
		}else {
			printlist[listindex] = selectNumber;
			printCombi(n,printlist,listindex+1,selectNumber+1);
			printCombi(n,printlist,listindex,selectNumber+1);
		}
	}
}
