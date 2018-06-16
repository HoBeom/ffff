#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
int main() {
	char word[50];
	int sort[27] = { 0 };
	int len;
	scanf("%s", word);
	len = strlen(word);
	//기수정렬
	for (int i = 0; i < len; i++) 
		sort[word[i] - 'a']++;	
	//
	for (int i = 0; i < 27; i++)
		for (int j = 0; j < sort[i]; j++)
			printf("%c", i + 'a');
	getchar();
	return 0;
}
