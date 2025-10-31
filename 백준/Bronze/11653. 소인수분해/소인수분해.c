#include <stdio.h>
char prime_under_100[] = { 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97 };
int main(void)
{
	long long int N, div;

	scanf(" %lld", &N);
	if (N == 1) return 0;

	char i = 0;
	while (1 < N && i < 25)
	{
		div = prime_under_100[i++];
		while (N % div == 0) printf("%lld\n", div), N /= div;
	}

	// 주어진 100 이하의 소수 연산으로 소인수분해가 끝나지 않은 경우,
	// 101 이상의 소수 중에서 약수를 탐색한다.
	div = 101;
	while (N != 1)
	{
		if (N < div * div) { printf("%lld", N); break; }
		while (N % div == 0) printf("%lld\n", div), N /= div;
		div += 2;
	}

	return 0;
}