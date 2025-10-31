#include <stdio.h>
int main()
{
	int A, sum = 0;
	for (int i = 0; i < 5; i++)
	{
		scanf(" %d", &A);
		sum += A * A;
	}
	printf("%d", sum % 10);

	return 0;
}