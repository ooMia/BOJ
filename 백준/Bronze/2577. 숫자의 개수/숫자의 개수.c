#include <stdio.h>
int main()
{
	short N[10] = { 0, };
	int res;
	do {
		int A, B, C;
		scanf(" %d %d %d", &A, &B, &C);
		res = A * B * C;
	} while (0);
	
	while (res != 0)
	{
		N[res % 10]++;
		res /= 10;
	}

	for (int i = 0; i < 10; i++)
		printf("%d\n", N[i]);
	
	return 0;
}