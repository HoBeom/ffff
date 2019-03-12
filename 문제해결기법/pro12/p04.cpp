#include <stdio.h>
#include <string.h>
#pragma warning(disable:4473)

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