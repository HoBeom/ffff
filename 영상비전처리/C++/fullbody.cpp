
#include <opencv2/opencv.hpp>
#include <objdetect/objdetect.hpp>

using namespace cv;
using namespace std;

void camerapedestrians(char *);
void peopledetector(char*, Mat);
CascadeClassifier pedcas;
VideoCapture cap;

int main(int argc, char* argv[]) {
	camerapedestrians("¸öµé");
	return(0);
}
String pedCasName = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_fullbody.xml";
void camerapedestrians(char *wcam) {
	Mat frame;
	bool stop(false);
	int fps = 1000;
	if (!pedcas.load(pedCasName)) {
		cerr << "full body load X" << endl; return;
	}
	//VideoCapture cap(0);
	VideoCapture cap("pedestrian-efpl-campus4-c0.avi");
	if (!cap.isOpened()) return;

	namedWindow(wcam, CV_WINDOW_AUTOSIZE);
	moveWindow(wcam, 100, 100);
	while (!stop) {
		cap >> frame;
		imshow(wcam, frame);
		peopledetector(wcam, frame);
		if (waitKey(1000 / fps) == 27) stop = true;
	}
	cap.release();
}

void peopledetector(char* wcam, Mat frame) {
	Mat framegray;
	vector<Rect> bodyRex;
	cvtColor(frame, framegray, CV_BGR2GRAY);
	equalizeHist(framegray, framegray);
	imshow("±Õ¸íµµ¿µ»ó", framegray);
	pedcas.detectMultiScale(framegray, bodyRex, 1.1, 2, 0, Size(30, 30));
	for (int i = 0; i < bodyRex.size(); i++) {
		Rect pr = bodyRex[i];
		rectangle(frame, Point(pr.x, pr.y), Point(pr.x + pr.width , pr.y + pr.height),Scalar(255,255,0), 3, 8, 0);
		Mat faceROI = framegray(pr);
	}
	imshow(wcam, frame);
}
