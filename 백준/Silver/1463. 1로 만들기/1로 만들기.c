#include <stdio.h>
char f(int n);
char REC[1000001] = { 0, };
int main(void)
{
	int N; scanf(" %d", &N);
	for (int i = 2; i <= N; ++i) f(i);
	printf("%hhd", REC[N]);
	return 0;
}
char f(int n)
{
	if (n == 1) return 0;

	char rec = REC[n];
	if (rec != 0) return rec;

	rec = f(n - 1);
	if (n % 2 == 0) rec = (f(n / 2) < rec) ? REC[n / 2] : rec;
	if (n % 3 == 0) rec = (f(n / 3) < rec) ? REC[n / 3] : rec;

	return (REC[n] = 1 + rec);
}