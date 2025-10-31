#include <stdio.h>
#include <string.h>

int main(void)
{
	int nPeople[15][15];

	for (int w = 1; w <= 14; w++) nPeople[0][w] = w;
	for (int h = 1; h <= 14; h++) nPeople[h][1] = 1;

	for (int h = 1; h <= 14; h++)
		for (int w = 2; w <= 14; w++)
			nPeople[h][w] = nPeople[h][w - 1] + nPeople[h - 1][w];

	int T;
	scanf(" %d", &T);

	int K, N;
	for (int t = 0; t < T; t++)
	{
		scanf(" %d %d", &K, &N);
		printf("%d\n", nPeople[K][N]);
	}
	return 0;
}