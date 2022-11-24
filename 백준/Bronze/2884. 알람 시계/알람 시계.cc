#include <stdio.h>
int main()
{
	int H, M;
	scanf(" %d %d", &H, &M);

	int total_min = 24 * 60 + H * 60 + M - 45;
	total_min %= 24 * 60;
	printf("%d %d", total_min / 60, total_min % 60);

	return 0;
}