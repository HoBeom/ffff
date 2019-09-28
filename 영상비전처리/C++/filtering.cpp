#include <opencv2/opencv.hpp>
#include <Windows.h>

using namespace cv;
using namespace std;

void meadianFilter(Mat, Mat);
void generateNoise(Mat, double);
void spacefilter(Mat, char*);
void binarizeImage(Mat);
void filter_lin(Mat, Mat, int);
int main(int argc, char* argv[]) {
	Mat img = imread("LenaRGB.bmp", IMREAD_GRAYSCALE);
	spacefilter(img, "공간필터링");
	return 0;
}
void spacefilter(Mat img, char *w) {
	// Mat img = imread(“LenaRGB.bmp”, IMREAD_GRAYSCALE);
	Mat img2 = Mat::zeros(Size(img.cols, img.rows), CV_8U);
	Mat img3 = Mat(img.size(), CV_32F, Scalar(0));
	Mat img4 = Mat(img.size(), CV_32F, Scalar(0));
	Mat img5 = Mat(img.size(), CV_32F, Scalar(0));
	Mat edge;

	char *w2 = "공필";
	namedWindow(w, WINDOW_AUTOSIZE);
	imshow(w, img);
	moveWindow(w, 100, 100);
	waitKey(0);

	//binarizeImage(img); imshow(w, img); waitKey(0);
	if (1) {
		//선형 공간필터링 (선형결합)
		filter_lin(img, img2, 0); imshow(w2, img2); waitKey(0);
		filter_lin(img, img2, 1); imshow(w2, img2); waitKey(0);
		filter_lin(img, img2, 2); imshow(w2, img2); waitKey(0);
		filter_lin(img, img2, 3); imshow(w2, img2); waitKey(0);
		filter_lin(img, img3, 4); img3.convertTo(img3, CV_8U); imshow("선명1", img3); waitKey(0);

	}
	else {
		//noise remove
		generateNoise(img, 0.1); imshow("잡음", img); waitKey(0);
		meadianFilter(img, img2); imshow("중필", img); waitKey(0);
	}
}void meadianFilter(Mat img, Mat res) {	int g[9];	for (int r = 1; r < img.rows - 1; r++) {		for (int c = 0; c < img.cols - 1; c++) {			int result = 0;			for (int i = 0; i < 3; i++) {				for (int j = 0; j < 3; j++) {					g[i * 3 + j] = img.at<uchar>(r + (i - 1), c + (j - 1)); //9 pix -> 1-Darray				}			}			for (int lastIndex = 7; lastIndex >= 0; lastIndex--) {				for (int k = 0; k <= lastIndex; k++) {					if (g[k] > g[k + 1]) {						int t = g[k];						g[k] = g[k + 1];						g[k + 1] = t;					}				}			}			res.at<uchar>(r, c) = g[4];				}	}}void generateNoise(Mat img, double rate) {	for (int i = 0; i < img.rows*img.cols*rate; i++) {		int r = rand() % img.rows;		int c = rand() % img.cols;		img.at<uchar>(r, c) = 255 - img.at<uchar>(r, c);	}}void binarizeImage(Mat img) {	for (int i = 0; i<img.rows; i++)
		for (int j = 0; j<img.cols; j++)
			if (img.at<uchar>(i, j) < 128)
				img.at<uchar>(i, j) = 0;
			else
				img.at<uchar>(i, j) = 255;}void filter_lin(Mat img, Mat resimg, int fter) {
	float wbook[8][3][3] = { { { 1. / 20, 2. / 20, 1. / 20 },{ 2. / 20, 8. / 20, 2. / 20 },{ 1. / 20, 2. / 20, 1. / 20 } },
	{ { 1. / 9, 1. / 9, 1. / 9 },{ 1. / 9, 1. / 9, 1. / 9 },{ 1. / 9, 1. / 9, 1. / 9 } },
	{ { 0, -1, 0 },{ -1, 4, -1 },{ 0, -1, 0 } },
	{ { 0, -2, 0 },{ -2, 8, -2 },{ 0, -2, 0 } },
	{ {0, -1, 0},{ -1, 5, -1 },{ 0, -1, 0 }},
	{ { -1, -1, -1 },{ -1, 9, -1 },{ -1, -1, -1 } },
	{ { -1, 0, 1 },{ -2, 0, 2 },{ -1, 0, 1 } }, 
	{ { -1, -2, -1 },{ 0, 0, 0 },{ 1, 2, 1 } }};

	for (int r = 1; r<img.rows - 1; r++) {
		for (int c = 1; c<img.cols - 1; c++) {
			int result = 0;
			//가중합계산
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					result += wbook[fter][i][j] * img.at<uchar>(r + (i - 1), c + (j - 1));
			if (fter >= 2) result = result / 2 + 128;
			resimg.at<uchar>(r, c) = result;
		}
	}
}