#include <stdio.h>
int main()
{
	int N, A, min, max;
	scanf(" %d %d", &N, &A);
	min = max = A;
	
	for (int i = 1; i < N; i++)
	{
		scanf("%d", &A);
		if (A < min) min = A;
		else if (max < A) max = A;
	}
	printf("%d %d", min, max);

	return 0;
}