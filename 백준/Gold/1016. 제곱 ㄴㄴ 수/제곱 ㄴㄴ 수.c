#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>
#include <malloc.h>
#include <string.h>

typedef unsigned long long ull;

ull min, max, count = 0;
char* isPrime, * isPrimeSquare;
int ip = 0, primes[78499];

void getPrimeSquaresInRange(ull to);
void getNonSquaresInRange(ull from, ull to);

int main() {

	scanf(" %lld %lld", &min, &max);

	size_t len_isPrime = sizeof(char) * (ull)sqrt((double)max) + 1;
	isPrime = (char*)malloc(len_isPrime);
	if (isPrime != NULL) memset(isPrime, -1, len_isPrime);

	size_t len_isNonSquare = sizeof(char) * max - min + 1;
	isPrimeSquare = (char*)malloc(len_isNonSquare);
	if (isPrimeSquare != NULL) memset(isPrimeSquare, -1, len_isNonSquare);

	getPrimeSquaresInRange(max);
	getNonSquaresInRange(min, max);
	printf("%lld", count);

	free(isPrime); free(isPrimeSquare);
	return 0;
}

// STEP 1
// (p*p <= to)를 만족하는 모든 소수 p를 vetor primes에 저장
void getPrimeSquaresInRange(ull to)
{
	if (4 <= to) primes[ip++] = 2;
	for (ull p = 3; p * p <= to; p += 2) {
		if (isPrime[p] != 0) {
			primes[ip++] = (int)p;
			for (ull pk = p * 2; pk * pk <= to; pk += p)
				if (isPrime[pk] == -1) isPrime[pk] = 0;
		}
	}
}

// STEP 2
// [from, to]에 존재하는 '제곱ㄴㄴ수' 세기
void getNonSquaresInRange(ull from, ull to)
{
	while(--ip >= 0) {
		ull pp = (ull)primes[ip] * primes[ip];
		for (ull ppk = from - from % pp; ppk <= to; ppk += pp)
			if (from <= ppk) isPrimeSquare[ppk - from] = 1;
	}
	for (ull i = 0; i < to - from + 1; i++)
		if (isPrimeSquare[i] != 1) count++;
}