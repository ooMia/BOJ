#include <stdio.h>
int main(void)
{
	int T;
	scanf(" %d", &T);

	int H, W, N;
	for (int t = 0; t < T; t++)
	{
		scanf(" %d %d %d", &H, &W, &N);

		int w = 1 + (N - 1) / H;
		int h = 1 + (N - 1) % H;

		printf("%d%02d\n", h, w);
	}
	return 0;
}