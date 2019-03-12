package alg05_1;

import java.util.Scanner;

public class QueueMain {
	public static void main(String[] args) {
		new QueueMain();
	}

	QueueMain() {
		Scanner kb = new Scanner(System.in);
		int N = kb.nextInt();
		int M = kb.nextInt();
		kb.close();
		test(N, M);
	}

	private void test(int N, int M) {
		PQueue pqueue = new PQueue(N);
		MyArray array = new MyArray(N);
		long Qstart, Qend, Astart, Aend;

		Qstart = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			pqueue.insert(randInt(N));
		}
		for (int i = 0; i < M; i++) {
			if (randInt(2) == 0 || pqueue.full())
				pqueue.extractMax();
			else
				pqueue.insert(randInt(N));
		}
		Qend = System.currentTimeMillis() - Qstart;

		Astart = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			array.insert(randInt(N));
		}
		for (int i = 0; i < M; i++) {
			if (randInt(2) == 0 || array.full())
				array.extractMax();
			else
				array.insert(randInt(N));
		}
		Aend = System.currentTimeMillis() - Astart;
		System.out.println("PQueue: " + Qend);
		System.out.println("Array: " + Aend);
	}

	private int randInt(int N) {
		return (int) (Math.random() * N);
	}

}
