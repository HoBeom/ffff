package alg04;

public class HeapSort {
	int heapsize;
	int[] heap;

	HeapSort(int[] array) {
		heap = array;
		heapsize = array.length-1;
		buildMaxHeap();
		heapSort();
		if(heap[0]>heap[1])
			swap(0,1);
	}

	private void heapSort() {
		if(heapsize<0)
			return;
		swap(0,heapsize--);
		maxHeapify(0);
		heapSort();
	}

	private void buildMaxHeap() {
		for (int i = heapsize; i >= 0 ; i--)
			maxHeapify(i);
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

	private void swap(int i, int j) {
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	/*
	 * private void heapSort(int[] array) { heapsize = array.length - 1;
	 * buildMaxHeap(array); inSort(array); }
	 * 
	 * private void inSort(int[] array) { if (heapsize > 2) { swap(array, 0,
	 * heapsize--); int i; if (array[1] > array[2]) i = 1; else i = 2; swap(array,
	 * 0, i); maxHeapify(array, i); inSort(array); } else { swap(array, 0, 2); if
	 * (array[0] > array[1]) swap(array, 0, 1); } }
	 * 
	 * private void swap(int[] array, int i, int j) { int temp = array[i]; array[i]
	 * = array[j]; array[j] = temp; }
	 * 
	 * private void buildMaxHeap(int[] array) { int i; for (i = 1; i < heapsize;
	 * i++) maxHeapify(array, i); if (array[1] > array[2]) i = 1; else i = 2; if
	 * (array[i] > array[0]) { swap(array, i, 0); maxHeapify(array, i); } }
	 * 
	 * private void maxHeapify(int[] array, int i) { if (i * 2 >= heapsize) return;
	 * int temp, k = 2 * i; if (array[k + 1] >= array[k]) k++; if (array[k] >
	 * array[i]) { swap(array, i, k); maxHeapify(array, k); } }
	 */
}
