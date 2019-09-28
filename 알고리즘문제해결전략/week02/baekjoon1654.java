import java.util.Scanner;
public class baekjoon1654 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int Lan[] = new int[input.nextInt()];
		int N = input.nextInt();
		for(int i = 0;i < Lan.length;i++)
			Lan[i] = input.nextInt();
		long i = 1, j = Integer.MAX_VALUE;
		for(int mid,count;i<=j;) {
			mid = (int) ((i+j)/2);
			count = 0;
			for (int k : Lan) 
				count += k/mid;
			if(count>=N)
				i = (long)mid+1;
			else if(count<N)
				j = (long)mid-1;
		}
		System.out.println(j);
	}
}
