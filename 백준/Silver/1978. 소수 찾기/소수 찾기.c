#include <stdio.h>

char isPrime[1001] = { 0, };

int main(void)
{
	isPrime[0] = isPrime[1] = -1;

	for (int n = 2; n <= 1000; n++)
	{
		if (isPrime[n] == 0) isPrime[n] = 1;
		for (int mul = 2; n * mul <= 1000; mul++)
			isPrime[n * mul] = -1;
	}
	int N, num, cnt = 0;
	scanf(" %d", &N);
	for (int n = 0; n < N; n++)
	{
		scanf(" %d", &num);
		switch (isPrime[num])
		{
		case 1: cnt++;
		case -1: default: break;
		}
	}
	printf("%d", cnt);
	return 0;
}