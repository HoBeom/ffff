package alg04;

public class MergeSort {
	void mergeSort(int[] array,int begin,int end){
		int middle;
		if(begin<end) {
			middle = (begin+end)/2;
			mergeSort(array,begin,middle);
			mergeSort(array,middle+1,end);
			
		}
	}
}
