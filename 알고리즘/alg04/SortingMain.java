package alg04;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

public class SortingMain {
	final int Random = 0, Reverse = 1;
	int[] RanT, RanM, RanB, RevT, RevM, RevB;

	public static void main(String[] args) {
		new SortingMain();
	}

	SortingMain(int teatmode) {
	}

	SortingMain() {
		RanT = makeRandomData(1000);
		RanM = makeRandomData(10000);
		RanB = makeRandomData(100000);
		RevT = makeReverseData(1000);
		RevM = makeReverseData(10000);
		RevB = makeReverseData(100000);

		System.out.print("\t\tRandom1000\tRevese1000");
		System.out.print("\tRandom10000\tRevese10000");
		System.out.println("\tRandom100000\tRevese100000");

		System.out.print("Bubble\t\t");
		bubbleTest(RanT);
		bubbleTest(RevT);
		bubbleTest(RanM);
		bubbleTest(RevM);
		// bubbleTest(RanB);
		// bubbleTest(RevB);
		System.out.println();

		System.out.print("Selection\t");
		selectTest(RanT);
		selectTest(RevT);
		selectTest(RanM);
		selectTest(RevM);
		// selectTest(RanB);
		// selectTest(RevB);
		System.out.println();

		System.out.print("Insetion\t");
		insertTest(RanT);
		insertTest(RevT);
		insertTest(RanM);
		insertTest(RevM);
		// insertTest(RanB);
		// insertTest(RevB);
		System.out.println();

		System.out.print("Merge\t\t");
		mergeTest(RanT);
		mergeTest(RevT);
		mergeTest(RanM);
		mergeTest(RevM);
		mergeTest(RanB);
		mergeTest(RevB);
		System.out.println();

		System.out.print("Quick1\t\t");
		quickTest1(RanT);
		quickTest1(RevT);
		quickTest1(RanM);
		quickTest1(RevM);
		//quickTest1(RanB);
		// quickTest1(RevB);
		System.out.println();

		System.out.print("Quick2\t\t");
		quickTest2(RanT);
		quickTest2(RevT);
		quickTest2(RanM);
		quickTest2(RevM);
		//quickTest2(RanB);
		// quickTest2(RevB);
		System.out.println();

		System.out.print("Quick3\t\t");
		quickTest3(RanT);
		quickTest3(RevT);
		quickTest3(RanM);
		quickTest3(RevM);
		//quickTest3(RanB);
		// quickTest3(RevB);
		System.out.println();
		
		System.out.print("Heapsort\t");
		HeapSort(RanT);
		HeapSort(RevT);
		HeapSort(RanM);
		HeapSort(RevM);
		//HeapSort(RanB);
		//HeapSort(RevB);
		System.out.println();
		
		System.out.print("Sort\t\t");
		Sort(RanT);
		Sort(RevT);
		Sort(RanM);
		Sort(RevM);
		Sort(RanB);
		Sort(RevB);
	}

	long Sort(int[] testdata) {
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			Arrays.sort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.Sort()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long HeapSort(int[] testdata) {
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			new HeapSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.HeapSort()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long quickTest3(int[] testdata) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			Q.ranquickSort(testdata);
			end += System.currentTimeMillis() - start;
			if (!isSort(testdata))
				System.out.println("SortingMain.quickTest3()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long quickTest2(int[] testdata) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			Q.midquickSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.quickTest2()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long quickTest1(int[] testdata) {
		long start, end = 0;
		QuickSort Q = new QuickSort();
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			Q.quickSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.quickTest1()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long mergeTest(int[] testdata) {
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			new MergeSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.mergeTest()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;

	}

	long insertTest(int[] testdata) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			S.insertSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.insertTest()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long selectTest(int[] testdata) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			S.selectSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.selectTest()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	long bubbleTest(int[] testdata) {
		SimpleSort S = new SimpleSort();
		long start, end = 0;
		int[] copydata;
		for (int i = 0; i < 10; i++) {
			copydata = copyData(testdata);
			start = System.currentTimeMillis();
			S.bubbleSort(copydata);
			end += System.currentTimeMillis() - start;
			if (!isSort(copydata))
				System.out.println("SortingMain.bubbleTest()");
		}
		System.out.printf("%10.5f \t", end / 1000.0);
		return end;
	}

	boolean isSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i] < data[i - 1]) {
				System.out.println(i + " " + data[i]);
				return false;
			}
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

	int[] copyData(int[] array) {
		int[] capydata = new int[array.length];
		for (int i = 0; i < array.length; i++)
			capydata[i] = array[i];
		return capydata;
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
