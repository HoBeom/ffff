#include <stdio.h>
#define MAX 100

/*
입력으로 N개의 수직 혹은 수평 선분이 주어진다. 
선분들간의 교차점의 좌표를 모두 계산하여 좌표에 대한 오름차순으로 정렬하여 출력하는 프로그램을 작성하라. 
x좌표가 동일한 경우에는 y좌표가 작은 점을 먼저 출력한다. 입력은 input3.txt 파일로부터 받는다. 
파일의 첫 줄에는 선분의 개수 N이 주어지고, 이어진 N줄에는 각 줄마다 하나의 선분의 시작점과 끝점의 좌표가 주어진다. 
수평 선분의 경우 좌표가 작은 점이 먼저 주어지고, 수직 선분의 경우 좌표가 작은 점이 항상 먼저 주어진다. 
수직이나 수평이 아닌 선분이 주어지는 경우는 없다. 수평 선분끼리 만나거나 혹은 수직 선분끼리 만나는 경우는 
교차점으로 간주하지 않는다. 이 문제를 해결하기 위해서 두 선분이 교차하는지 검사하는 함수intersect를 만들어 사용하라. 
하나의 수평선분 L과 수직선분 V가 교차하는지 검사하는 한 가지 방법은 L의 양 끝점이 V의 좌우에 나누어져 있고, 
또한 V의 양끝점이 L의 상하에 나누어져 있으면 교차하고, 그렇지 않으면 교차하지 않는다. 
매개 변수로 두 선분을 받아서 교차하면 1, 그렇지 않으면 0을 반환하라. 어떤 전역변수도 사용해서는 안된다
*/
struct Line
{
	int x1; 
	int y1; 
	int x2;
	int y2;
};
struct Point
{
	int x;
	int y;
};
int intersect(Line L,Line V) {
	if (L.x1 <= V.x1&&L.x2 >= V.x1&&V.y1 <= L.y1&&V.y2 >= L.y1)
		return 1;
	return 0;
}
bool x_horizontal(Line x) {
	if (x.y1 == x.y2)
		return true;
	return false;
}
bool insert(Point data[], Point x, int n) {
	if (n >= MAX)
		return false;
	if (n == 0) {
		data[0] = x;
		return true;
	}
	while (n > 0 && data[n - 1].x > x.x)
		data[n] = data[n-- - 1];
	while (n > 0 && data[n - 1].x == x.x&&data[n - 1].y > x.y)
		data[n] = data[n-- - 1];
	data[n] = x;
	return true;
}
int main() {
	Line L[MAX];
	Line V[MAX];
	int LN=0, VN=0;
	Point cross[MAX];
	int n;
	FILE *fp;
	fopen_s(&fp, "input3.txt", "r");
	Line tmp;
	fscanf_s(fp, "%d", &n);
	for (int i = 0; i < n; i++) {
		fscanf_s(fp, "%d %d %d %d", &tmp.x1, &tmp.y1, &tmp.x2, &tmp.y2);
		if (x_horizontal(tmp))
			L[LN++] = tmp;
		else
			V[VN++] = tmp;
	}
	n = 0;
	Point p;
	for (int i = 0; i < LN; i++) {
		for (int j = 0; j < VN; j++) {
			if (intersect(L[i], V[j])) {
				p.x = V[j].x1;
				p.y = L[i].y1;
				if (insert(cross, p, n))
					n++;
			}
		}
	}
	for (int i = 0; i < n; i++)
		printf("%d %d\n", cross[i].x, cross[i].y);
	getchar(); getchar();
	return 0;
}