int K, N;
unsigned arr[10000], inputMax = 0;

unsigned calc(unsigned localMax) {
	int res = 0;
	for (int i = 0; i < K; i++) res += arr[i] / localMax;
	return res;
}

unsigned getGlobalMax()
{
	unsigned localMax, from = 1, to = 1 + inputMax;
	while (to - from > 1) {
		localMax = from + (to - from) / 2;
		if (calc(localMax) >= N) from = localMax, localMax *= 2;
		else to = localMax;
	}
	return from;
}

#include <stdio.h>
int main()
{
	// INPUT
	scanf(" %d %d", &K, &N);
	for (int i = 0; i < K; i++) {
		scanf(" %d", arr + i);
		if (inputMax < arr[i]) inputMax = arr[i];
	}
	printf("%d", getGlobalMax());
}