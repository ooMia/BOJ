#include <stdio.h>
int main()
{
	int N;
	scanf(" %d", &N);
	if (N == 1) { printf("*"); return 0; }

	for (int r = 0; r < N; r++)	{
		for (int s = 0; s <= 1; s++) {
			for (int i = 0; i < N; i++)	{
				if (i % 2 == s) printf("*");
				else if (i == N - 1) break;
				else printf(" ");
			}
			printf("\n");
		}
	}
	return 0;
}