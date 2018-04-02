package alg04;

public class MergeSort {
	int[] temp;
	
	MergeSort() {
	}

	MergeSort(int[] array) {
		//합병하면서 배열을 계속 할당받는것보다 처음 호출시 배열을 생성하여 사용 
		//생성시 함수들이 메모리에 올라가는 시간도 소팅시간에 포함.
		//무엇이 최적화인지 테스트 해볼것!!
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
