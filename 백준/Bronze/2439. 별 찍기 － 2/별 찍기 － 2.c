#include <stdio.h>
int main()
{
	int N;
	scanf(" %d", &N);

	for (int line = 1; line <= N; line++)
	{
		for (int blank = N-line; blank > 0; blank--) printf(" ");
		for (int star = 0; star < line; star++) printf("*");
		printf("\n");
	}
	
	return 0;
}