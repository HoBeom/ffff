#include <stdio.h>
#include <string.h>
#define MAX_WORD 4000
#define MAX_LEN 20
#pragma warning(disable:4996)
char dict[MAX_WORD][MAX_LEN];
char grid[MAX_LEN][MAX_LEN];
int n_words;
void read_dictionary();
void print_dict();
void read_grid();
void print_grid();
int search(int);
int offset[][2] = { {0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1} };
int main() {
	read_dictionary();
	read_grid();
	for (int i = 0; i < n_words; i++) {
		if (search(i))
			printf("%s\n", dict[i]);
	}
	
	printf("exit");
	getchar();
	return 0;
}
int search(int index) {
	int len = strlen(dict[index]);
	for (int i = 0; i < MAX_LEN; i++) {
		for (int j = 0; j < MAX_LEN; j++) {
			for (int k = 0; k < 8; k++) {
				int x = i; int y = j;
				char word[MAX_LEN] = { '\0' };
				for (int l = 0; l < len; l++) {
					if (x < 0 || y < 0 || x >= MAX_LEN || y >= MAX_LEN)
						break;
					word[l] = grid[x][y];
					x += offset[k][0];
					y += offset[k][1];
				}
				if (strcmp(word, dict[index]) == 0)
					return 1;
			}
		}
	}
	return 0;
}
void read_dictionary() {
	n_words = 0;
	FILE *in_fp;
	fopen_s(&in_fp, "dictionary.txt", "r");
	while (fscanf(in_fp, "%s", dict[n_words++]) != EOF);
	fclose(in_fp);
}
void read_grid() {
	FILE *in_fp;
	fopen_s(&in_fp, "grid.txt", "r");
	for (int i = 0; i < MAX_LEN; i++) {
		for (int j = 0; j < MAX_LEN; j++) {
			fscanf(in_fp, "%c", &grid[i][j]);
		}
	}
	fclose(in_fp);
}
void print_grid() {
	for (int i = 0; i < MAX_LEN; i++) {
		for (int j = 0; j < MAX_LEN; j++) {
			printf("%c ", grid[i][j]);
		}
		printf("\n");
	}
}
void print_dict() {
	for (int i = 0; i < n_words; i++)
			printf("%s\n", dict[i]);
}