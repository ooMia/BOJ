#include <stdio.h>

int f(int n) {
	if (n < 0) return 0;
	else if (n == 0) return 1;
	else return f(n - 1) + f(n - 2) + f(n - 3);
}

int main(void)
{
	int T, N; scanf(" %d", &T);
	for (int t = 0; t < T; ++t) scanf(" %d", &N), printf("%d\n", f(N));
	return 0;
}