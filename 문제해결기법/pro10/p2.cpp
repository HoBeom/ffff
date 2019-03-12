#include <stdio.h>
#define MAX 100

/*
키보드로 부터 연속해서 정수들을 입력받는다. 
정수가 하나 씩 입력될 때 마다 현재까지 입력된 정수들을 오름차순으로 정렬하여 화면에 출력한다.
단, 새로 입력된 정수가 이미 배열에 저장되어 있다면 추가하는 대신 “duplicate entry”라고 출력한다. 
사용자가 -1을 입력하면 프로그램을 종료한다. 이 일을 하기 위해서 다음과 같은 두 함수를 작성하라. 
먼저 현재까지 입력된 정수들이 저장되어 있는 배열에 새로 입력된 정수가 이미 존재하는지 검사하여 
만약 있으면 그 위치(배열 인덱스)를 반환하고, 없으면 -1을 반환하는 함수 find를 작성하라. 
또한 새로 입력된 정수가 duplicate entry가 아닌 경우에 이 정수를 배열에 오름차순으로 정렬되도록 삽입하는 
함수 insert를 작성하라. 그리고 main 함수에서는 이 두 함수를 적절히 이용하여 문제를 해결하라. 
Duplicate entry라고 출력할 때 그 배열에서 그 정수의 위치를 함께 출력하라. 어떤 전역 변수도 사용해서는 안된다.
*/

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
