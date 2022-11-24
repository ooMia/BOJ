#include <stdio.h>
int main(void)
{
	int A, B, C;
	scanf(" %d %d %d", &A, &B, &C);
	
	int profit = C - B;
	printf("%d", (profit <= 0)?-1: A / profit + 1);
	return 0;
}