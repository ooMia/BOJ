#include <stdio.h>
int main()
{
	int N, sum = 0;
	scanf(" %d", &N);

	char c;
	for (int i = 0; i < N; i++)
	{
		scanf(" %c", &c);
		sum += (c-'0');
	}
	printf("%d", sum);

	return 0;
}