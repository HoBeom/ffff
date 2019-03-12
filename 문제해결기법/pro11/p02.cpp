#include <stdio.h>
#define MAX 100
int check(double array[MAX][MAX], double b[MAX], int n);
int main() {
	double array[MAX][MAX];
	double b[MAX];
	int n;
	FILE *fp;
	fopen_s(&fp, "data.txt", "r");
	fscanf_s(fp, "%d", &n);
	//파일에서 행렬을 읽는다.
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) 
			fscanf_s(fp, "%lf", &array[i][j]);
		fscanf_s(fp, "%lf", &b[i]);
	}
	//행렬을 단위행렬로 변환
	if (check(array, b, n)) {
		for (int i = 0; i < n; i++)
			printf("x%d = %lf\n", i + 1, b[i]);
	}
	getchar(); getchar();
}
int check(double array[MAX][MAX], double b[MAX], int n) {
	double div, mul;
	//대각선 아래 제거
	for (int i = 0; i < n; i++) {
		div = array[i][i];
		// div == 0 일때  열 swap 필요!! 
		if (div == 0) {
			for (int j = 0; j <= n; j++) {
				//행이 모두 0 이라면 해가 0이면 infinite 해가 0이아니면 No
				if (j == n) {
					if(b[i]==0)
						printf("Infinite solution");
					else
						printf("No solution");
					return 0;
				}
				//행에 0이 아닌 원소가 있다면 swap
				if (array[i][j] != 0)
					break;
			}
			//swap
			printf("swap\n");
			int k = i + 1;
			double temp;
			while (array[i][i] == 0 && k <= n) {
				if (k == n) {
					printf("No sulution");
					return 0;
				}
				for (int j = 0; j < n; j++) {
					temp = array[k][j];
					array[k][j] = array[i][j];
					array[i][j] = temp;
				}
				temp = b[k];
				b[k] = b[i];
				b[i] = temp;
				k++;
			}
			div = array[i][i];
		}
		//행을 나누어 준다.
		for (int j = 0; j < n; j++)
			array[i][j] /= div;
		b[i] /= div; // 해를 나누어 준다.
		
		//i행은 i,i가 1인 행이 되었으므로 다른 행의 i열을 0으로 만들어준다.
		for (int j = i + 1; j < n; j++) {
			mul = array[j][i];
			for (int k = i; k < n; k++) {
				array[j][k] -= array[i][k] * mul;
			}
			b[j] -= b[i] * mul;
		}
	}
	//대각선 위쪽 제거
	for (int i = n - 1; i >= 0; i--) {
		for (int j = i - 1; j >= 0; j--) {
			b[j] -= b[i] * array[j][i];
			array[j][i] = 0;
		}
	}
	//단위 행열로 변환 완료
	return 1;
}