#include<opencv2/opencv.hpp>
#include <opencv\highgui.h>
#include <Windows.h>


using namespace cv;
using namespace std;
void edgedetectors(char *);
void CannyThresh(int, void*);
void houghliner();
void houghlineseg();
int lowThr; // T1 Canny 전역변수
Mat gim, cadge;
char *wemap = "모서리맵";
void camerashooter(char*);
void drawREBounds(Mat canny_oput, vector<vector<Point>> contours, vector<Vec4i> hierarchy, Mat drawing);
Mat locateFaces(Mat mface);
int main(int argc, char* argv[]) {
	//edgedetectors("정상");
	camerashooter("방송중");
	return 0;
}
void camerashooter(char *wcam) {
	Mat frame;
	boolean stop(false);
	int fps = 30;

	VideoCapture cap(0);
	if (!cap.isOpened()) { printf("카메라오류"); return; }
	int wide = (int)cap.get(CV_CAP_PROP_FRAME_WIDTH);
	int high = (int)cap.get(CV_CAP_PROP_FRAME_HEIGHT);
	namedWindow(wcam, CV_WINDOW_AUTOSIZE);
	moveWindow(wcam, 100, 100);
	Mat face;
	//read a frame and display it 
	while (!stop) {
		cap >> frame;
		imshow(wcam, frame);
		face = locateFaces(frame);
		imshow("얼굴", face);
		if (waitKey(1000 / fps) >= 0) //useraction 을 1000/fps만큼 기다리고 그동안에 입력이 들어오면 종료된다.
			stop = true;
	}
	cap.release();
}

void edgedetectors(char* w0) {
	Mat img = imread("OntarioHighway.png", CV_LOAD_IMAGE_COLOR);

	if (img.empty()) { cout << "사진없음" << endl; return; }
	namedWindow(w0, CV_WINDOW_AUTOSIZE);
	imshow(w0, img);
	moveWindow(w0, 100, 100);
	waitKey(0);
	// make it gray & smooth it
	cvtColor(img, gim, CV_RGB2GRAY);
	char *w1 = "모서리";
	imshow(w1, gim);
	waitKey(0);

	GaussianBlur(gim, gim, Size(3, 3), 0, 0, BORDER_DEFAULT);
	imshow(w1, gim);
	waitKey(0);

	// edge detection : Sobel, Laplavian, Canny
	Mat gradX, gradY, ddgim;
	Mat absGradX, absGradY, gradim;
	Mat edge;
	int scale = 1, delta = 0, ddepth = CV_16S;
	int ftype = 0;
	if (ftype == 1) {
		//Sobel
		Sobel(gim, gradX, ddepth, 1, 0, 3, BORDER_DEFAULT);
		convertScaleAbs(gradX, absGradX);
		imshow(w1, absGradX); waitKey(0);

		Sobel(gim, gradY, ddepth, 0, 1, 3, BORDER_DEFAULT);
		convertScaleAbs(gradY, absGradY);
		imshow(w1, absGradY); waitKey(0);

		addWeighted(absGradX, 0.5, absGradY, 0.5, delta, gradim);
		imshow(w1, gradim); waitKey(0);

		threshold(gradim, edge, 128, 255, THRESH_BINARY);
		imshow(wemap, edge); waitKey(0);
	}
	else if (ftype == 2) {
		// Laplacian
		Laplacian(gim, ddgim, ddepth, 3, scale, 0, BORDER_DEFAULT);
		convertScaleAbs(ddgim, gradim);
		imshow(w1, gradim); waitKey(0);
	}
	else {
		//Canny
		namedWindow(wemap, CV_WINDOW_AUTOSIZE);
		createTrackbar("minThr", wemap, &lowThr, 100, CannyThresh);//100 = max_lowThr ,callback function = CannyThresh
		CannyThresh(0, 0);//초기화
		waitKey(0);
	}

}
#define PI 3.1416
void CannyThresh(int, void*) {
	int ratio = 3, kernelsz = 3;
	int T1 = 55;
	Canny(gim, cadge, T1, ratio*T1, 3, kernelsz);
	//houghliner(); //직선
	houghlineseg(); //선분
	imshow(wemap, cadge);
}

void houghliner() {
	vector<Vec2f> lines;
	int M = cadge.rows, N = cadge.cols;

	HoughLines(cadge, lines, 1, PI / 180 * 1, 100 + lowThr);
	vector<Vec2f>::iterator it = lines.begin();
	cvtColor(cadge, cadge, CV_GRAY2RGB);
	while (it != lines.end()) {
		float r = (*it)[0], q = (*it)[1]; // 거리, 각도
		if (q < PI*0.25 || q > PI*0.75) { // 수직
			Point p1(r / cos(q), 0), p2((r - M*sin(q)) / cos(q), M);
			line(cadge, p1, p2, Scalar(255, 0, 255), 1);
		}
		else if (q <PI * 70 / 180 || q >PI * 110 / 180) { //수평
			Point p1(0, r / sin(q)), p2(N, (r - N*cos(q)) / sin(q));
			line(cadge, p1, p2, Scalar(0, 255, 255), 1);
		}
		++it;
	}

}

void houghlineseg() {
	vector<Vec4i> linsegs;
	HoughLinesP(cadge, linsegs, 1, PI / 180, 50 + lowThr, 40, 20);
	vector<Vec4i>::iterator it = linsegs.begin();
	cvtColor(cadge, cadge, CV_GRAY2RGB);
	while (it != linsegs.end()) {
		Point p1((*it)[0], (*it)[1]), p2((*it)[2], (*it)[3]);
		line(cadge, p1, p2, Scalar(0, 255, 255), 2);
		++it;
	}
}

void drawREBounds(Mat canny_oput, vector<vector<Point>> contours, vector<Vec4i> hierarchy, Mat drawing) {
	vector<RotatedRect> minRect(contours.size());
	vector<RotatedRect> minEllipse(contours.size());
	RNG rng(12345);
	//- compute Rects and Ellipses -
	for (int i = 0; i < contours.size(); i++) {
		if (contours[i].size() > 100) {
			minRect[i] = minAreaRect(Mat(contours[i]));//가장작은감싸는 사각형
			minEllipse[i] = fitEllipse(Mat(contours[i]));//가장작은 감싸는 타원
		}
	}
	// -draw Rects and Ellipses-
	for (int i = 0; i < contours.size(); i++) {
		Scalar color = Scalar(0, 255, 255);
		ellipse(drawing, minEllipse[i], color, 1, 8);
		//사각형그려주는함수가 없음.
		Point2f rect_points[4];
		minRect[i].points(rect_points);
		for (int j = 0; j < 4; j++) {
			line(drawing, rect_points[j], rect_points[(j + 1) % 4], color, 1, 8);
		}
	}
}
Mat locateFaces(Mat mface) {
	Mat canny_oput;
	vector<vector<Point>> contours;
	vector<Vec4i> hierarchy;
	RNG rng(12345);//random number generation
	int thresh = 100;//for Canny

	Canny(mface, canny_oput, thresh, thresh * 2, 3); //aperture
	findContours(canny_oput, contours, hierarchy, CV_RETR_TREE, CV_CHAIN_APPROX_SIMPLE, Point(0, 0));
	//draw contours opengl
	Mat drawing = Mat::zeros(canny_oput.size(), CV_8UC3);
	for (int i = 0; i < contours.size(); i++) {
		//랜덤색을 생성.rgb
		Scalar color = Scalar(255, 255, 0);
		drawContours(drawing, contours, i, color, 2, 8, hierarchy, 0, Point());
		//1=thick(테두리의 굵기
		//8=line_type
	}
	drawREBounds(canny_oput, contours, hierarchy, drawing);
	return drawing;
}
