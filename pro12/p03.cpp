#include <stdio.h>
#include <string.h>
#pragma warning(disable:4996)
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