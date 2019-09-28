
import java.util.Scanner;

public class p10 {
	public static void main(String[] args) {
		/*
		 * 문제 10: 입력으로 두 양의 정수 N과 K를 받은 후 1에서 N까지의 정수들 중에서 서로 다른 K개의 정수를 선택 하는 모든 경우를
		 * 나열하여 한 줄에 하나씩 출력하는 프로그램을 작성하라.  단 출력되는 순서는 달라져도
		 * 무방하고 N은 20이하이고 K?N이다. 입력은 키보드로 부터 받는 다.
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
