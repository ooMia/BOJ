#include <stdio.h>
int f(int n);
int fh(int n);
int main(void)
{
	int N; scanf(" %d", &N); printf("%d\n", f(N));
	return 0;
}
int f(int n)
{
	if (n <= 2) return n;
	else if (n % 2 == 0) return 2 * f(n / 2);
	else return 2 * fh(n / 2);
}
int fh(int n)
{
	if (n <= 2) return 1;
	else if (n % 2 == 0) return 2 * fh(n / 2) - 1;
	else return 2 * f(n / 2 + 1) - 1;
}