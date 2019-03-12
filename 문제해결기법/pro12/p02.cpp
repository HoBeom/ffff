#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
//하나의 영문 소문자로 구성된 문자열을 입력받은 후 문자열을 구성하는 
//문자들을 알파벳 순으로 정렬하여 만들어지는 문자열을 출력하라. 예를 들어 hello가 입력되면 ehllo를 출력한다. 
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
