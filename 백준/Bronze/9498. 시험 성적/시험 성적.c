#include <stdio.h>
int main()
{
	int S;
	scanf(" %d", &S);

	if (S < 60) printf("F");
	else if (S < 70) printf("D");
	else if (S < 80) printf("C");
	else if (S < 90) printf("B");
	else printf("A");

	return 0;
}