#include <stdio.h>
#include <string.h>
#include <math.h>

char buf[10000 + 1];
void printDecoded(char* str) {
	int sq = sqrt(strlen(str));
	for (int row = 0; row < sq; row++)
		for (int col = 1; col <= sq; col++)
			printf("%c", str[sq * col - row - 1]);
}
int main() {
	int N; scanf(" %d", &N);
	for (int n = 0; n < N; n++)
		scanf(" %s", &buf), printDecoded(buf), puts("");
	return 0;
}