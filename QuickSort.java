package alg04;

public class QuickSort {
	
	public int partition(int[] array, int begin, int end) {
		int pivot, temp, Left, Right, t;
		Left = begin;
		Right = end;
		pivot = (begin + end) / 2;
		while (Left < Right) {
			while ((array[Left] <= array[pivot]) && (Left <= Right))
				Left++;
			while ((array[Right] > array[pivot]) && (Left <= Right))
				Right--;
			if (Left <= Right) {
				temp = array[Left];
				array[Left] = array[Right];
				array[Right] = temp;
			}
			if (Right == pivot) {
				return Left;
			}
		}
		temp = array[pivot];
		array[pivot] = array[Right];
		array[Right] = temp;
		return Right;
	}

	public void MidPivotSort(int[] array, int begin, int end) {
		if (begin < end) {
			int p;
			p = partition(array, begin, end);
			MidPivotSort(array, begin, p - 1);
			MidPivotSort(array, p + 1, end);
		}
	}
}
