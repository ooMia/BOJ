#include <stdio.h>
int main(void)
{
	int N;
	scanf(" %d", &N);

	int lv = 1;
	while (1) {
		N -= lv;
		if (N <= 0) break;
		else lv++;
	}

	printf("%d/%d",
		(lv % 2 == 0) ? lv + N : 1 - N,
		(lv % 2 == 0) ? 1 - N : lv + N);
	return 0;
}