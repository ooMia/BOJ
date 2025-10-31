#include <stdio.h>
int main(void)
{
	int N, K;
	scanf(" %d %d", &N, &K);

	int n = N, k = 0, i = 0;

	char people[1001] = { -1,0, };
	printf("<");

	while (n > 0)
	{
		while (k != K) {
			if (N < ++i) i = 1;
			if (people[i] == 0) ++k;
		}
			
		if (n == 1) printf("%d>", i);
		else printf("%d, ", i);

		people[i] = -1;
		k = 0;
		--n;
	}

	return 0;
}