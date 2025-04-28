#include <stdio.h>
int main(void)
{
	char N; scanf("%hhd", &N);
	char line, blank, star;
	for (line = 0; line < N; ++line) {
		for (blank = 0; blank < line; ++blank) printf(" ");
		for (star = N - line; star > 0; --star) printf("*");
		puts("");
	}
	return 0;
}