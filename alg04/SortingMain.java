package alg04;

public class SortingMain {

	public static void main(String[] args) {
		new SortingMain(1);
		// new SortingMain();
	}

	SortingMain(int TestMode) {
		quickTest(10);
	}

	SortingMain() {
		long[][] time = new long[7][6];

	}

	long quickTest(int testdataSize) {
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = makeRandomData(testdataSize);
			start = System.currentTimeMillis();
			new QuickSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("정렬실패");
			printData(testdata);
		}
		System.out.println(testdataSize + "length quick sort: " + end / 1000.0 + "sec");
		return end;
	}

	long mergeTest(int testdataSize) {
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = makeRandomData(testdataSize);
			start = System.currentTimeMillis();
			new MergeSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("정렬실패");
		}
		System.out.println(testdataSize + "length Merge sort: " + end / 1000.0 + "sec");
		return end;

	}

	long insertTest(int testdataSize) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = makeRandomData(testdataSize);
			start = System.currentTimeMillis();
			S.insertSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("정렬실패");
		}
		System.out.println(testdataSize + "length insertion sort: " + end / 1000.0 + "sec");
		return end;
	}

	long selectTest(int testdataSize) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = makeRandomData(testdataSize);
			start = System.currentTimeMillis();
			S.selectSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("정렬실패");
		}
		System.out.println(testdataSize + "length Selection sort: " + end / 1000.0 + "sec");
		return end;
	}

	long bubbleTest(int testdataSize) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = makeRandomData(testdataSize);
			start = System.currentTimeMillis();
			S.bubbleSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("정렬실패");
		}
		System.out.println(testdataSize + "length bubble sort: " + end / 1000.0 + "sec");
		return end;
	}

	boolean isSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i] < data[i - 1])
				return false;
		}
		return true;
	}

	void printData(int[] print) {
		for (int i : print) {
			System.out.println(i);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}

	int[] makeRandomData(int N) {
		int[] randomdata = new int[N];
		for (int i = 0; i < randomdata.length; i++)
			randomdata[i] = (int) (Math.random() * N) + 1;
		return randomdata;
	}

	int[] makeReverseData(int N) {
		int[] reversedata = new int[N];
		for (int i = reversedata.length, j = 0; i > 0; i--, j++)
			reversedata[j] = i;
		return reversedata;
	}
}
