package alg04;

public class QuickSort {

	public void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public void midquickSort(int[] array) {
		midquickSort(array, 0, array.length - 1);
	}

	public void ranquickSort(int[] array) {
		ranquickSort(array, 0, array.length - 1);
	}

	private void quickSort(int[] array, int begin, int end) {
		if (begin < end) {
			int p;
			p = partition(array, begin, end);
			quickSort(array, begin, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private void ranquickSort(int[] array, int begin, int end) {
		if (begin < end) {
			int p;
			p = ranpartition(array, begin, end);
			quickSort(array, begin, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private void midquickSort(int[] array, int begin, int end) {
		if (begin < end) {
			int p;
			p = midpartition(array, begin, end);
			quickSort(array, begin, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private int partition(int[] array, int begin, int end) {
		int Left = begin - 1;
		int pivot = array[end];
		int temp;
		for (int Right = begin; Right < end; Right++) {
			if (array[Right] < pivot) {
				Left++;
				temp = array[Left];
				array[Left] = array[Right];
				array[Right] = temp;
			}
		}
		Left++;
		array[end] = array[Left];
		array[Left] = pivot;
		return Left;
	}

	private int ranpartition(int[] array, int begin, int end) {
		//랜덤으로 인덱스를 골라 end 인덱스와 치환
		int ran = begin + (int) (Math.random() * (end - begin));
		int pivot = array[ran];
		array[ran] = array[end];
		int Left = begin - 1;
		int temp;
		for (int Right = begin; Right < end; Right++) {
			if (array[Right] < pivot) {
				Left++;
				temp = array[Left];
				array[Left] = array[Right];
				array[Right] = temp;
			}
		}
		Left++;
		array[end] = array[Left];
		array[Left] = pivot;
		return Left;
	}

	private int midpartition(int[] array, int begin, int end) {
		// x,y,z 의 대소를 비교하여 중간값을 end로 보낸다.
		int mid = (begin + end) / 2;
		int x = array[begin];
		int y = array[end];
		int z = array[mid];
		int temp;
		if ((y > x && x > z) || (z > x && x > y)) {
			// begin = 중간값.
			array[end] = x;
			array[begin] = y;
		} else if ((x > z && z > y) || (y > z && z > y)) {
			// mid = 중간값.
			array[end] = z;
			array[mid] = y;
		} // else 는 end가 중간값이므로 swap할 필요가 없다.
		int pivot = array[end];
		int Left = begin - 1;
		for (int Right = begin; Right < end; Right++) {
			if (array[Right] < pivot) {
				Left++;
				temp = array[Left];
				array[Left] = array[Right];
				array[Right] = temp;
			}
		}
		Left++;
		array[end] = array[Left];
		array[Left] = pivot;
		return Left;
	}
}
