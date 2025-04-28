#include <stdio.h>
int main()
{
	int N[3];
	scanf(" %d %d %d", &N[0], &N[1], &N[2]);

	int min = N[0], max = N[0], sum = N[0] + N[1] + N[2];
	for (int i = 1; i < 3; i++)
	{
		if (N[i] < min) min = N[i];
		else if (max < N[i]) max = N[i];
		else { printf("%d", N[i]); return 0; }
	}
	printf("%d", sum - min - max);
	return 0;
}