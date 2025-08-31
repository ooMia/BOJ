#include <stdio.h>
int main()
{
	int X, Y;
	scanf(" %d %d", &X, &Y);

	short Q;
	if (X > 0)
		Q = (Y > 0) ? 1 : 4;
	else
		Q = (Y > 0) ? 2 : 3;

	printf("%d", Q);
	return 0;
}