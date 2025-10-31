#include <stdio.h>
int main()
{
	int a, b;
	scanf("%d %d", &a, &b);

	printf("%d.", a / b);
	a %= b;
	for (int i = 0; i < 30; i++)
	{
		if (a == 0) break;
		a *= 10;
		printf("%d", a / b);
		a %= b;
	}
	return 0;
}