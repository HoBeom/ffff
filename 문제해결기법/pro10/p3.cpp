#include <stdio.h>
#define MAX 100
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