#include <stdio.h>

int f(int n)
{
	int res = n;
	while (n != 0) res += n % 10, n /= 10;
	return res;
}

int main(void)
{
	int N; scanf(" %d", &N);
	for (int i = (N > 54) ? N - 54 : 1; i < N; i++)
		if (f(i) == N) { printf("%d", i); return 0; }
	printf("0"); return 0;
}