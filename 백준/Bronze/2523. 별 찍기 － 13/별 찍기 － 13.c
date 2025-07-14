#include <stdio.h>
int main()
{
	int N;
	scanf(" %d", &N);

	int line = 1, star = 1;
	while (line <= 2 * N - 1)
	{
		for (int i = 0; i < star; i++) printf("*");
		printf("\n");

		star += (line < N) ? +1 : -1;
		line++;
	}

	return 0;
}