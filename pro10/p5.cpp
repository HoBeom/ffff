#include <stdio.h>
#define MAX 100
enum direction
{
	iy = 0,
	ix = 1,
	dy = 2,
	dx = 3,
}dir; 
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
bool x_horizontal(Line L) {
	if (L.y1 == L.y2)
		return true;
	return false;
}
int intersect(Line L, Line V) {
	if (x_horizontal(V)) {
		Line temp = L;
		L = V;
		V = temp;
	}
	if (L.x1 <= V.x1&&L.x2 >= V.x1&&V.y1 <= L.y1&&V.y2 >= L.y1)
		return 1;
	return 0;
}
Line mkLine(Point p1, Point p2) {
	Line L;
	if (p1.y == p2.y) {
		L.y1 = L.y2 = p1.y;
		if (p1.x < p2.x) {
			L.x1 = p1.x;
			L.x2 = p2.x;
		}
		else
		{
			L.x1 = p2.x;
			L.x2 = p1.x;
		}
	}
	else {
		L.x1 = L.x2 = p1.x;
		if (p1.y < p2.y) {
			L.y1 = p1.y; 
			L.y2 = p2.y;
		}
		else {
			L.y1 = p2.y;
			L.y2 = p1.y;
		} 
	}
	return L;
}
int check(Point Path[],int n,Point p) {
	Line L = mkLine(p, Path[n - 1]);
	bool L_horizon = x_horizontal(L);
	Line V;
	bool V_horizon;
	for (int i = 0; i < n - 2; i++) {
		V = mkLine(Path[i], Path[i + 1]);
		V_horizon = x_horizontal(V);
		if (V_horizon^L_horizon) {
			if (intersect(L, V))
				return 0;
		}
	}
	return 1;
}
int main() {
	Point Path[MAX];
	int n = 0,temp, predir;
	Point p;
	direction dir;
	while (scanf_s("%d %d", &dir, &temp)) {
		if (dir == -1 && temp == -1)
			break;
		if (n == 0) {
			p.x = 0;
			p.y = 0;
			Path[n++] = p;
		}
		else {
			if ((predir + 2) % 4 == dir) {
				printf("invalid move\n");
				continue;
			}
			p.x = Path[n - 1].x;
			p.y = Path[n - 1].y;
		}
		switch (dir)
		{
		case iy:
			p.y += temp;
			break;
		case ix:
			p.x += temp;
			break;
		case dy:
			p.y -= temp;
			break;
		case dx:
			p.x -= temp;
			break;
		default:
			printf("input error\n");
			continue;
		}
		if (check(Path, n, p)) {
			Path[n++] = p;
			printf("%d %d", p.x, p.y);
			predir = dir;
		}
		else
			printf("invalid move");
		printf("\n");
	}
	return 0;
}
