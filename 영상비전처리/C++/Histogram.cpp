#include <opencv.hpp>
#include <windows.h>

using namespace cv;
using namespace std;

void rgb_histogrammer(Mat src);
void histogrammer(Mat src, char *whist, int chan);
void equalize_histogra(char *weq, Mat img, int x, int y);
void hsv_skinner(Mat src, int wx, int wy);

int W = 300, H = 200;

int main(int argc, char* argv[])
{
	char *w0 = "입력";
	Mat img, grim;
	char *fn = "lena.png";
	char *fn2 = "faces_lessenthusia.jpg";

	img = imread(fn2, CV_LOAD_IMAGE_COLOR);
	if (img.empty()) { cout << "File Not Found" << endl; return -1; }

	H = img.rows; W = img.cols; // min(W, img.cols);
	namedWindow(w0, 1); //window name (name, window_normal=0, window_autosize=1) normal은 사이즈 조절가능
	imshow(w0, img); //img show
	moveWindow(w0, 50, 50); //window 배치
	cvtColor(img, grim, COLOR_BGR2GRAY); // color BGR 2 Gray
	histogrammer(grim, "H0", -1);
	rgb_histogrammer(img);
	waitKey(0); //시간을 매개변수로 줄수도 있음

	hsv_skinner(img, 50 + (16, W) * 2, 50);
	waitKey(0);
	return 0;
}
void histogrammer(Mat src, char *whist, int chan) {
	const int hSize[1] = { 256 };
	int channels[1] = { 0 };
	float hrange[2] = { 0., 256. };
	const float* ranges[1];
	ranges[0] = hrange;
	Mat hist;
	
	calcHist(&src, 1, channels, Mat(), hist, 1, hSize, ranges);
	
	double HWhi = 100, maxval = 0;
	Mat histIm(HWhi, hSize[0], CV_8U, Scalar(255)); // Histgr 영상 생성
	
	minMaxLoc(hist, 0, &maxval, 0, 0); // minv,maxv, minloc,maxloc
	double barscale = HWhi / maxval; // height scaler (for histogram plot)
	// - 막대 그래프 그리기 (onto histIm) –
	for (int b = 0; b < hSize[0]; b++) {
		float binVal = hist.at<float>(b);
		int h = (int)(binVal *barscale);
		line(histIm, Point(b, hSize[0]), Point(b, HWhi - h), Scalar(0), 1); //선(이미지, (Point) X, (Point) Y, 색, 굵기)
	}
	//간격 설정을 위한 cx,cy이다.
	int cx = chan == -1 ? 1 : chan;
	int cy = chan == -1 ? 0 : 2;
	namedWindow(whist, 1);
	imshow(whist, histIm);
	moveWindow(whist, 50 + (W + 16)*cx, 50 + (38 + H)*cy);//imshow 이후에 와야한다. 전에 오면 마지막으로 창이뜬위치에 뜨게됨
}

void rgb_histogrammer(Mat src) {
	char *wC1 = "Blue", *wC2 = "Green", *wC3 = "Red"; //이름
	vector<Mat> vex; //Mat 제네릭 벡터 vex
	cv::split(src, vex); // rgb 값을 벡터에 저장
	int Wp = W + 16, Hp = H + 38; //간격 설정
	// BGR이미지 출력								  
	namedWindow(wC1, 1);
	imshow(wC1, vex[0]);
	moveWindow(wC1, 50 , 50 + Hp);

	namedWindow(wC2, 1);
	imshow(wC2, vex[1]);
	moveWindow(wC2, 50+Wp, 50 + Hp);

	namedWindow(wC3, 1);
	imshow(wC3, vex[2]);
	moveWindow(wC3, 50+Wp*2, 50 + Hp);

	//각각의 BGR의 Histogram
	histogrammer(vex[0], "Hb", 0);
	histogrammer(vex[1], "Hg", 1);
	histogrammer(vex[2], "Hr", 2);

	equalize_histogra("EqHist[blue]", vex[0], 50 + Wp * 3, 50 + Hp);//blue 벡터를 equalize 한다.
}

void equalize_histogra(char *weq, Mat img, int x, int y) {
	Mat eqHim; //equalize할 이미지 eqHim
	equalizeHist(img, eqHim); //opencv 내장함수 (img->,eqHim)equalize
	//eqHim 이미지 출력
	namedWindow(weq, 1);
	imshow(weq, eqHim);
	moveWindow(weq, x, y);
	//eqHim의 histogram 출력
	histogrammer(eqHim, "HEQ", 3);//equalize 하여 Histogram 간격이 조정됨을 알수 있다
}
void hsv_skinner(Mat src, int wx, int wy) {
	Mat hsv, dst_bgr;
	char *w = "HS(V=255)", *wskin = "HS-skin";
	vector<Mat> vex;
	int hminax[2] = { 160,10 }, sminax[2] = { 25,166 }; //피부값의 범위를 정해준다. hminax 색상 , sminax 채도
	cvtColor(src, hsv, COLOR_BGR2HSV);
	split(hsv, vex);
	vex[2] = 255;//V channel = 255 - focus only on(H,S)-  명도를 없앤다.
	merge(vex, hsv);
	cvtColor(hsv, dst_bgr, CV_HSV2BGR);
	namedWindow(w, 1); imshow(w, dst_bgr);
	moveWindow(w, wx, wy);
	Mat hmask, smask, mask2, skinmask;
	//-hue masking-
	threshold(vex[0], hmask, hminax[1], 255, THRESH_BINARY_INV); //below hmina
	threshold(vex[0], mask2, hminax[0], 255, THRESH_BINARY); //above hminax[1]
	if (hminax[0] < hminax[1])
		hmask = hmask & mask2;
	else hmask = hmask | mask2;
	// -sat masking -
	threshold(vex[1], smask, sminax[1], 255, THRESH_BINARY_INV); //below sminax
	threshold(vex[1], mask2, sminax[0], 255, THRESH_BINARY); //above sminax
	smask = smask & mask2;
	skinmask = hmask & smask;

	Mat skindetect(src.size(), CV_8UC3, Scalar(0, 0, 0));// (size, 3채널 색 , 배경색 검정); 이미지버퍼생성
	src.copyTo(skindetect, skinmask);//마스킹하며 카피
	namedWindow(wskin, 1);
	imshow(wskin, skindetect);
	moveWindow(wskin, wx + W + 16, wy);
}