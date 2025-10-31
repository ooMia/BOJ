#include <stdio.h>
int main()
{
	int N, M, i;
	scanf(" %d", &N);
	
	M = N, i = 0;
	int A, B, C;
	while (1)
	{
		i++;

		A = (M < 10) ? 0 : M / 10;
		B = M % 10;
		C = A + B;

		M = B * 10 + C % 10;
		if (M == N) break;
	}
	printf("%d", i);
	return 0;
}