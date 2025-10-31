#include <stdio.h>
int main(void)
{
	int N;
	scanf(" %d", &N);

	int level = 0;
	int min = 1, max = 1;
	while (1)
	{
		if (N <= max) break;
		else {
			level++;
			min = max + 1;
			max += 6 * level;
		}
	}
	printf("%d", level + 1);
	return 0;
}