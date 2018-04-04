#include <gl\glut.h>
#include <stdio.h>

GLint TopLeftX, TopLeftY, BottomRightX, BottomRightY;
GLint WinW = 300, WinH = 300;
void MyDisplay() {
	glViewport(0, 0, WinW, WinH);
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(0.5, 0.5, 0.5);
	glBegin(GL_POLYGON);
	int x = WinW / 2;
	int y = WinH / 2;
	glVertex3f((TopLeftX-x) / (float)x, (y - TopLeftY) / (float)y, 0.0);
	glVertex3f((TopLeftX-x) / (float)x, (y - BottomRightY) / (float)y, 0.0);
	glVertex3f((BottomRightX-x) / (float)x, (y - BottomRightY) / (float)y, 0.0);
	glVertex3f((BottomRightX-x) / (float)x, (y - TopLeftY) / (float)y, 0.0);
	glEnd();
	glFlush();
}
void MyReshape(int NewWidth, int NewHeight) {
	WinW = NewWidth;
	WinH = NewHeight;
	printf("%d\t%d\n", WinW, WinH);
}

void MyMouseClick(GLint Button, GLint State, GLint X, GLint Y) {
	if (Button == GLUT_LEFT_BUTTON && State == GLUT_DOWN) {
		TopLeftX = X;
		TopLeftY = Y;
		printf("%d\t%d\n", X, Y);
	}
}

void MyMouseMove(GLint X, GLint Y) {
	BottomRightX = X;
	BottomRightY = Y;
	glutPostRedisplay();
}

int main(int argc, char** argv) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB);
	glutInitWindowSize(300, 300);
	glutInitWindowPosition(0, 0);
	glutCreateWindow("OpenGL Drawing Example");
	glClearColor(1.0, 1.0, 1.0, 1.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
	glutDisplayFunc(MyDisplay);
	glutMouseFunc(MyMouseClick);
	glutMotionFunc(MyMouseMove);
	glutReshapeFunc(MyReshape);
	glutMainLoop();
	return 0;
}


