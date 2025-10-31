#include <stdio.h>

int calcResult(int N, int K)
{
	static int history[201][201] = { 0 };
	int* res = &history[N][K];

	// 이미 계산하여 저장된 값 참조
	if (*res != 0) return *res;

	// 기본값
	switch (N) {
	case 0: return (*res = 1);
	case 1: return (*res = K);
	case 2: return (*res = K * (K + 1) / 2);
	}
	switch (K) {
	case 0: return (*res = 0);
	case 1: return (*res = 1);
	case 2: return (*res = N + 1);
	}
	
	// 계산하여 값 저장
	unsigned long long result = 0;
	for (int n = 0; n <= N; n++)
		result += calcResult(n, K - 1);
	return (*res = result % 1000000000);
}

int main()
{
	int N, K;
	scanf(" %d %d", &N, &K);
	printf("%d", calcResult(N, K));
	return 0;
}
