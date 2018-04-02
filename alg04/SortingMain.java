package alg04;

public class SortingMain {
	final int Random = 0, Reverse = 1;

	public static void main(String[] args) {
		new SortingMain();
	}
	SortingMain(int teatmode){
	}

	SortingMain() {
		System.out.print("\t\tRandom1000\tRevese1000");
		System.out.print("\tRandom10000\tRevese10000");
		System.out.println("\tRandom100000\tRevese100000");
		
		System.out.print("Bubble\t\t");
		bubbleTest(1000, Random);
		bubbleTest(1000, Reverse);
		bubbleTest(10000, Random);
		bubbleTest(10000, Reverse);
		//bubbleTest(100000, Random);
		//bubbleTest(100000, Reverse);
		System.out.println();
		
		System.out.print("Selection\t");
		selectTest(1000, Random);
		selectTest(1000, Reverse);
		selectTest(10000, Random);
		selectTest(10000, Reverse);
		//selectTest(100000, Random);
		//selectTest(100000, Reverse);
		System.out.println();
		
		System.out.print("Insetion\t");
		insertTest(1000, Random);
		insertTest(1000, Reverse);
		insertTest(10000, Random);
		insertTest(10000, Reverse);
		//insertTest(100000, Random);
		//insertTest(100000, Reverse);
		System.out.println();
		
		System.out.print("Merge\t\t");
		mergeTest(1000, Random);
		mergeTest(1000, Reverse);
		mergeTest(10000, Random);
		mergeTest(10000, Reverse);
		mergeTest(100000, Random);
		mergeTest(100000, Reverse);
		System.out.println();
		
		System.out.print("Quick1\t\t");
		quickTest1(1000, Random);
		quickTest1(1000, Reverse);
		quickTest1(10000, Random);
		quickTest1(10000, Reverse);
		quickTest1(100000, Random);
		//quickTest1(100000, Reverse);
		System.out.println();
		
		System.out.print("Quick2\t\t");
		quickTest2(1000, Random);
		quickTest2(1000, Reverse);
		quickTest2(10000, Random);
		quickTest2(10000, Reverse);
		quickTest2(100000, Random);
		//quickTest2(100000, Reverse);
		System.out.println();
		
		System.out.print("Quick3\t\t");
		quickTest3(1000, Random);
		quickTest3(1000, Reverse);
		quickTest3(10000, Random);
		quickTest3(10000, Reverse);
		quickTest3(100000, Random);
		//quickTest3(100000, Reverse);
		System.out.println();
	}

	long quickTest3(int testdataSize, int mode) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			Q.ranquickSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.quickTest3()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;
	}

	long quickTest2(int testdataSize, int mode) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			Q.midquickSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.quickTest2()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;
	}

	long quickTest1(int testdataSize, int mode) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			Q.quickSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.quickTest1()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;
	}

	long mergeTest(int testdataSize, int mode) {
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			new MergeSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.mergeTest()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;

	}

	long insertTest(int testdataSize, int mode) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			S.insertSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.insertTest()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;
	}

	long selectTest(int testdataSize, int mode) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			S.selectSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.selectTest()");
		}
		System.out.print(end/1000.0+"\t\t");
		return end;
	}

	long bubbleTest(int testdataSize, int mode) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		for (int i = 0; i < 10; i++) {
			int[] testdata = null;
			if (mode == Random)
				testdata = makeRandomData(testdataSize);
			else
				testdata = makeReverseData(testdataSize);
			start = System.currentTimeMillis();
			S.bubbleSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.bubbleTest()");
		}
		System.out.print(end/1000.0+"\t\t");
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
			System.out.print(i + " ");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
		System.out.println();
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
