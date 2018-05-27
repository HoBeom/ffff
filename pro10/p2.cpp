#include <stdio.h>
#define MAX 100
int find(int data[], int x,int n) {
	int L = 0;
	int R = n - 1;
	int mid;
	while (L <= R) {
		mid = (L + R) / 2;
		if (data[mid] > x)
			R = mid - 1;
		else if (data[mid] < x)
			L = mid + 1;
		else
			return mid;
	}
	return -1;
}
bool insert(int data[],int x,int n) {
	if (n >= MAX)
		return false;
	if (n == 0) {
		data[0] = x;
		return true;
	}
	while (n > 0 && data[n-1] > x)
		data[n] = data[n---1];
	data[n] = x;
	return true;
}
int main() {
	int data[MAX];
	int n=0,temp,index;
	while (scanf_s("%d", &temp)) {
		if (temp == -1)
			break;
		index = find(data, temp, n);
		if (index == -1) {
			if (insert(data, temp, n))
				n++;
			for (int i = 0; i < n; i++)
				printf("%d ", data[i]);
		}
		else
			printf("Duplicate entry: %d", index);
		printf("\n");
	}
	return 0;
}
