#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include <queue>

const int maxInput = 10000;

char* initArray(int size, int value) {
	char* isPrime = (char*)malloc(size + 1);
	if (isPrime != NULL) memset(isPrime, value, size + 1);
	return isPrime;
}

std::queue<int> getPrimesInRange(int from, int to)
{
	char* isPrime = initArray(to, -1);

	std::queue<int> primes;
	if (from <= 2 && 2 <= to) primes.push(2);
	for (int i = 3; i <= to; i += 2) {
		if (isPrime[i]) {
			if (from <= i) primes.push(i);
			for (int j = i * 2; j <= to; j += i) isPrime[j] = 0;
		}
	}
	return primes;
}

int main()
{
	int N;

	while (true) {
		scanf(" %d", &N);
		if (N == 0) break;
		auto primes = getPrimesInRange(N + 1, 2 * N);
		printf("%zd\n", primes.size());
	}
	return 0;
}