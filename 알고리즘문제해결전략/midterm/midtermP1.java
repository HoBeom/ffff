import java.util.Scanner;

public class midtermP1 {
	//문제 1번 소스코드입니다. 201430189 전호범
	public static void main(String[] args) {
		Scanner kb = new Scanner(midtermP1.class.getResourceAsStream("input1.txt"));
		int T = kb.nextInt();
		while (0 < T--) {
			//입력 받을때 배열의 크기를 미리 주기때문에 배열의 크기를 필요한만큼만 할당하였습니다.
			int[][] edge = new int[kb.nextInt()][];
			for (int i = 0; i < edge.length; i++) {
				edge[i] = new int[kb.nextInt()];
				for (int j = 0; j < edge[i].length; j++) {
					edge[i][j] = kb.nextInt();
				}
			}
			//동일한 정점을 두번이상 지나지 않는다.
			boolean path[] = new boolean[edge.length];
			//출발정점 start, 도착정점 end, 경로의 길이 L
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
		//현재 지점을 방문으로 체크후 현재 지점에서 end까지 갈수있는 경로의 개수를 계산후 리턴
		int sum = 0;
		for (int i = 0; i < edge[start].length; i++)
			sum += countPath(edge,path, edge[start][i], end, L, degree + 1);
		path[start]=false;
		return sum;
	}

}
