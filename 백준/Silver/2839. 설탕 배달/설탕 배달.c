#include <stdio.h>
int main(void)
{
	int N;
	scanf(" %d", &N);

	int p5, p3;
	p5 = N / 5, N %= 5;
	switch (N)
	{
	case 1: p3 = 2, p5 -= 1; break;
	case 2: p3 = 4, p5 -= 2; break;
	case 3: p3 = 1; break;
	case 4: p3 = 3, p5 -= 1; break;
	default: p3 = 0;
	}

	printf("%d", (p5 >= 0 && p3 >= 0) ? p3 + p5 : -1);
	return 0;
}