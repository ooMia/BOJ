#include <stdio.h>
int main(void)
{
	int N, res = 0; scanf(" %d", &N);
	for (int n = 5, tmp = n; n <= N; tmp = ++n)
		while (tmp % 5 == 0) ++res, tmp /= 5;
	printf("%d", res);
	return 0;
}