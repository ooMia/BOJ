#include <stdio.h>
#include <malloc.h>
#include <string.h>

#include <vector>

const int maxRange = 10000;

char* initCharArray(size_t size, int value) {
	char* arr = (char*)malloc(size + 1);
	if (arr != NULL) memset(arr, -1, size);
	return arr;
}

void initPrimeSeive(char* const seive, const int size) {
	seive[0] = seive[1] = 0;
	for (int i = 3; i <= size; i += 2) {
		if (seive[i])
			for (int j = i * 2; j <= size; j += i) seive[j] = 0;
	}
}

std::vector<int> getPrimesInRange(const char* seive, int from, int to) {
	std::vector<int> primes;
	if (from <= 2 && 2 <= to) primes.push_back(2);
	from += (from + 1) % 2;

	for (int i = from; i <= to; i += 2)
		if (seive[i]) primes.push_back(i);

	return primes;
}


int main()
{
	int T; scanf(" %d", &T);

	char* seive = initCharArray(maxRange, -1);
	initPrimeSeive(seive, maxRange);

	for (int t = 0; t < T; ++t)
	{
		int N; scanf(" %d", &N);
		auto primes = getPrimesInRange(seive, 2, N/2);

		int primeA, primeB;
		do {
			primeA = primes.back(), primes.pop_back();
			primeB = N - primeA;
		} while (seive[primeB] == 0);
		printf("%d %d\n", primeA, primeB);
	}

	return 0;
}