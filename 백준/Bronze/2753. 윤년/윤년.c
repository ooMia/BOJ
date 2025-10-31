#include <stdio.h>
int main()
{
	int Y;
	scanf(" %d", &Y);
	printf("%d\n", (Y % 400 == 0 || (Y % 4 == 0 && Y % 100 != 0)) ? 1 : 0);
	return 0;
}