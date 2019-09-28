import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException{
		ArrayDeque<data> dq = new ArrayDeque<data>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = input.nextInt(), L = input.nextInt();
		for (int i = 0; i < N; i++) {
			int x = input.nextInt();
			while (!dq.isEmpty() && dq.getLast().x >= x)
				dq.removeLast();
			dq.addLast(new data(i, x));
			if(dq.peek().i<=i-L) dq.pop();
			bw.write(dq.peek().x+" ");
		}
		bw.flush();
	}

}

class data {
	int i;
	int x;

	data(int i, int x) {
		this.i = i;
		this.x = x;
	}
}