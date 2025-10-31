#include <stdio.h>
int main()
{
	int A, B;
	while (1)
	{
		if (scanf(" %d %d", &A, &B) == EOF) break;
		printf("%d\n", A + B);
	}
	
	return 0;
}