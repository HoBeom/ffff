#include <stdio.h>
#define MAX 100

/*
5번 [Self avoiding walk] 2차원 평면에서 원점 (0,0)에서 출발한다. 
사용자가 현재의 위치에서 상하좌우 어떤 한 방향으로 얼마 만큼 이동하라는 명령을 내리면 그렇게 이동한다.
명령은 두 음이 아닌 정수로 표현된다. 우선 방향은 0, 1, 2, 3으로 표시하고 0은 y좌표가 증가하는 방향, 
1은 x좌표가 증가하는 방향, 2는 y좌표가 감소하는 방향, 그리고 3은 x좌표가 감소하는 방향이다. 
예를 들어 2 7은 y좌표가 7만큼 감소하는 위치로 이동하라는 명령이다. 
프로그램은 사용자가 현재까지 이동한 궤적을 기억하고 있어야 한다. 
사용자가 내린 명령대로 이동했을 때 민약 지금까지 이동한 궤적과 교차하면 invalid move라고 출력하고
이동 명령을 거부한다. 만약 그렇지 않으면 명령대로 이동하고 이동한 점의 좌표를 출력한다. 
사용자가 -1 -1을 입력할 때 까지 이 일을 계속한다. 사용자가 -1 -1을 입력하면 프로그램을 종료한다. 
이 문제를 해결하기 위해 사용자가 내린 명령이 invalid move인지 아닌지 검사하는 함수 check를 작성하라. 
전역변수를 사용해서는 안된다.
*/

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
