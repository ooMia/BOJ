#include <stdio.h>
int main()
{
	int A, max_val = 0, max_idx = 0;

	for (int i = 1; i <= 9; i++)
	{
		scanf(" %d", &A);
		if (max_val < A) max_val = A, max_idx = i;
	}
	printf("%d\n%d", max_val, max_idx);

	return 0;
}