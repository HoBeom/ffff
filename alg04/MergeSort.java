package alg04;

public class MergeSort {
	int[] temp;

	MergeSort(){}
	MergeSort(int[] array) {
		temp = new int[array.length];
		mergeSort(array, 0, array.length - 1);
	}

	public void mergeSort(int[] array) {
		temp = new int[array.length];
		mergeSort(array, 0, array.length - 1);
	}

	private void mergeSort(int[] array, int begin, int end) {
		int middle;
		if (begin < end) {
			middle = (begin + end) / 2;
			mergeSort(array, begin, middle);
			mergeSort(array, middle + 1, end);
			merge(array, begin, middle, end);
		}
	}

	private void merge(int[] array, int begin, int mid, int end) {
		int i, j, k;
		i = begin;
		j = mid + 1;
		k = begin;
		while (i <= mid && j <= end) {
			if (array[i] <= array[j])
				temp[k] = array[i++];
			else
				temp[k] = array[j++];
			k++;
		}
		while (j <= end)
			temp[k++] = array[j++];
		while (i <= mid)
			temp[k++] = array[i++];
		for (i = begin; i <= end; i++) {
			array[i] = temp[i];
		}
	}
}
