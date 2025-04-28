#include <stdio.h>
int main()
{
	int N;
	scanf(" %d", &N);

	int line = 1, nSpace = 0, nStar;
	while(line <= 2 * N - 1)
	{
		nStar = 2 * N - 1 - 2 * nSpace;
		for (int i = 0; i < nSpace; i++) printf(" ");
		for (int i = 0; i < nStar; i++) printf("*");
		printf("\n");

		nSpace += (line < N) ? +1 : -1;
		line++;
	}
	return 0;
}