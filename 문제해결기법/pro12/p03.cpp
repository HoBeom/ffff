#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)

//아나그램(anagram)이란 문자들의 순서를 재배열하여 동일하게 만들 수 있는 문자열을 말한다. 대소문자는 구분하지 않는다. 
//예를 들어서 Silent와 Listen은 아나그램이다. 입력으로 두 문자열을 받아서 아나그램인지 판단하는 프로그램을 작성하라.
char tolower(char a) {
	if (a < 'a')
		return a + 32;
	return a;
}
int main() {
	char word1[50],word2[50];
	int sort[27] = { 0 };
	int len;
	scanf("%s", word1);
	scanf("%s", word2);
	len = strlen(word1);

	if (len != strlen(word2)) {
		printf("NO");
		return 0;
	}
	//기수정렬
	for (int i = 0; i < len; i++) {
		word1[i] = tolower(word1[i]);
		word2[i] = tolower(word2[i]);
		sort[word1[i] - 'a']++;
		sort[word2[i] - 'a']--;
	}	
	for (int i = 0; i < 27; i++) {
		if (sort[i] != 0) {
			printf("NO");
			return 0;
		}
	}
	printf("YES");
	getchar();
	return 0;
}