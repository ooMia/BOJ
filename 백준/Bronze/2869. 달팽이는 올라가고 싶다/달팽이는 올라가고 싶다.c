#include <stdio.h>
int main(void)
{
	int A, B, V;
	scanf(" %d %d %d", &A, &B, &V);

	int last = V - A, speed = A - B;
	int day = last / speed + (last % speed != 0);
	printf("%d", day + 1);
	
	return 0;
}