#include <stdio.h>
int main()
{
	int N;
	short R[42] = { 0, };
	short cnt = 0;
	for (int i = 0; i < 10; i++)
	{
		scanf(" %d", &N);
		if (R[N % 42]) continue;
		else R[N % 42] = 1, cnt++;
	}
	printf("%d", cnt);
}