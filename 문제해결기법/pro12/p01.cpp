#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
int main() {
	char word[50];
	scanf("%s", word);
	for (int i = strlen(word)-1; i >= 0; i--)
		printf("%c", word[i]);
	getchar();
	return 0;
}