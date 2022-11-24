#include <stdio.h>

int main(void)
{
	int x[3], y[3];
	for (int i = 0; i < 3; ++i)
		scanf(" %d %d", x + i, y + i);
	
	int ix = 0, iy = 0;
	for (int i = 1; i < 3; ++i) {
		if (x[0] == x[i]) ix = 3 - i;
		if (y[0] == y[i]) iy = 3 - i;
	}

	printf("%d %d", x[ix], y[iy]);
	return 0;
}