package alg04;

public class QuickSort {
	int i;
	
	QuickSort(){}
	QuickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public void quickSort(int[] array) {
		quickSort(array, 0, array.length);
	}

	public void quickSort(int[] array, int begin, int end) {
		System.out.println(++i + "step " + begin + "," + end);
		if (begin < end) {
			int p;
			p = partition(array, begin, end);
			quickSort(array, begin, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	public int partition(int[] array, int begin, int end) {
		int pivot, temp, Left, Right, t;
		System.out.println("partition " + begin + " to " + end);
		Left = begin - 1;
		Right = begin;
		pivot = end;
		t = array[pivot];
		while (Right < pivot) {
			if (array[Right] <= t) {
				temp = array[++Left];
				array[Right] = array[Left];
				array[Left] = temp;
				System.out.println("swap"+Right+","+Left);
			}
			Right++;
		}
		Left++;
		temp = array[Left];
		array[Right] = array[Left];
		array[Left] = temp;
		return Left;

	}

	public int ranpartition(int[] array, int begin, int end) {
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

}
