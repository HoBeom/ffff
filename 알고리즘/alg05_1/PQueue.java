package alg05_1;

public class PQueue {
	int heapsize;
	int[] heap;

	PQueue() {
		this(100);
	}

	PQueue(int N) {
		heapsize = 0;
		heap = new int[N];
	}

	boolean insert(int x) {
		if (!full()) {
			heap[heapsize] = x;
			int i = heapsize, P;
			while (i != 0) {
				P = getP(i);
				if (heap[i] > heap[P])
					swap(i, P);
				else
					break;
				i = P;
			}
			heapsize++;
			//printHeap();
			return true;
		}
		System.out.println("is Full");
		return false;
	}

	boolean extractMax() {
		if (!empty()) {
			heap[0] = heap[--heapsize];
			maxHeapify(0);
			//printHeap();
			return true;
		} else
			System.out.println("is empty");
		return false;
	}

	private void maxHeapify(int i) {
		int L = (i * 2) + 1;
		int R = (i * 2) + 2;
		int temp = i;
		if (L <= heapsize && heap[L] > heap[i])
			temp = L;
		if (R <= heapsize && heap[R] > heap[temp])
			temp = R;
		if (temp != i) {
			swap(i, temp);
			maxHeapify(temp);
		}
	}

	boolean full() {
		if (heapsize == heap.length)
			return true;
		else
			return false;
	}

	boolean empty() {
		if (heapsize == 0)
			return true;
		else
			return false;
	}

	private void printHeap() {
		for (int i = 0; i < heapsize; i++)
			System.out.printf("%d ", heap[i]);
		System.out.println();
	}

	private int getP(int x) {
		return (x - 1) / 2;
	}

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

}
