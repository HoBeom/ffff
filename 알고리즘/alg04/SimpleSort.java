package alg04;

public class SimpleSort {

	public void bubbleSort(int[] array) {
		int temp;
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array.length - i; j++) {
				if (array[j] < array[j - 1]) {
					temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}

	public void selectSort(int[] array) {
		int min,temp;
		for(int i = 0; i < array.length-1;i++) {
			min = i;
			for(int j = i+1;j<array.length;j++) {
				if(array[j]<array[min])
					min = j;
			}
			temp = array[min];
			array[min]=array[i];
			array[i] = temp;
		}
	}
	public void insertSort(int[] array) {
		int temp,point;
		for(int i = 1 ; i < array.length ; i++) {
			temp = array[i];
			point = i-1;
			while (point >=0 && array[point]>temp) {
				array[point+1] = array[point];
				point--;
			}
			array[point+1]=temp;
		}
	}
}
