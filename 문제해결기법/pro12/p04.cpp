#include <stdio.h>
#include <string.h>
#pragma warning(disable:4473)

//입력으로 텍스트파일 input.txt를 읽어서 왼쪽 정렬하여 output.txt 파일로 출력하는 프로그램을 작성하라. 
//출력 파일의 한 줄은 80 문자를 초과해서는 안되며, 단어를 자르지 않는 한도 내에서 가능한 한
//최대한 80 문자에 가깝도록 맞춘다.
int main() {
	char ch;
	int count = 0;
	FILE *in_fp,*out_fp;
	fopen_s(&in_fp, "input.txt", "r");
	fopen_s(&out_fp, "output.txt", "w");

	while (fscanf_s(in_fp, "%c", &ch) != EOF) {
		if (ch == ' ' && count > 80) {
			fprintf_s(out_fp,"\n");
			count = 0;
		}
		else {
			fprintf_s(out_fp, "%c", ch);
			count++;
		}
	}
	fclose(in_fp);
	fclose(out_fp);
	return 0;
}