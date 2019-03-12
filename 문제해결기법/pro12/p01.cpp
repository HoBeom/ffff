#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
// 입력으로 하나의 문자열을 받은 후 뒤집어서 출력하는 프로그램을 작성하라. 예를 들어 hello를 입력하면 olleh가 출력된다.
int main() {
	char word[50];
	scanf("%s", word);
	for (int i = strlen(word)-1; i >= 0; i--)
		printf("%c", word[i]);
	getchar();
	return 0;
}