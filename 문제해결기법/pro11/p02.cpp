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
	//���Ͽ��� ����� �д´�.
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) 
			fscanf_s(fp, "%lf", &array[i][j]);
		fscanf_s(fp, "%lf", &b[i]);
	}
	//����� ������ķ� ��ȯ
	if (check(array, b, n)) {
		for (int i = 0; i < n; i++)
			printf("x%d = %lf\n", i + 1, b[i]);
	}
	getchar(); getchar();
}
int check(double array[MAX][MAX], double b[MAX], int n) {
	double div, mul;
	//�밢�� �Ʒ� ����
	for (int i = 0; i < n; i++) {
		div = array[i][i];
		// div == 0 �϶�  �� swap �ʿ�!! 
		if (div == 0) {
			for (int j = 0; j <= n; j++) {
				//���� ��� 0 �̶�� �ذ� 0�̸� infinite �ذ� 0�̾ƴϸ� No
				if (j == n) {
					if(b[i]==0)
						printf("Infinite solution");
					else
						printf("No solution");
					return 0;
				}
				//�࿡ 0�� �ƴ� ���Ұ� �ִٸ� swap
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
		//���� ������ �ش�.
		for (int j = 0; j < n; j++)
			array[i][j] /= div;
		b[i] /= div; // �ظ� ������ �ش�.
		
		//i���� i,i�� 1�� ���� �Ǿ����Ƿ� �ٸ� ���� i���� 0���� ������ش�.
		for (int j = i + 1; j < n; j++) {
			mul = array[j][i];
			for (int k = i; k < n; k++) {
				array[j][k] -= array[i][k] * mul;
			}
			b[j] -= b[i] * mul;
		}
	}
	//�밢�� ���� ����
	for (int i = n - 1; i >= 0; i--) {
		for (int j = i - 1; j >= 0; j--) {
			b[j] -= b[i] * array[j][i];
			array[j][i] = 0;
		}
	}
	//���� �࿭�� ��ȯ �Ϸ�
	return 1;
}