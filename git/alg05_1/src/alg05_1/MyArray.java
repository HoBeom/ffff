package alg05_1;

public class MyArray {
	int[] array;
	int arraysize;

	MyArray() {
		this(100);
	}

	MyArray(int N) {
		array = new int[N];
		arraysize = 0;
	}

	boolean insert(int x) {
		if (!full()) {
			array[arraysize++] = x;
			return true;
		} else {
			System.out.println("is Full");
			return false;
		}
	}

	boolean extractMax() {
		if (!empty()) {
			int maxindex=0, max = -1;
			for(int i = 0; i < arraysize;i++) {
				if(array[i]>max)
					maxindex = i;
			}
			array[maxindex] = array[--arraysize];
			return true;
		}
		else {
			System.out.println("is Empty");
			return false;
		}
			
	}

	boolean full() {
		if (arraysize == array.length)
			return true;
		else
			return false;
	}

	boolean empty() {
		if (arraysize == 0)
			return true;
		else
			return false;
	}

}
