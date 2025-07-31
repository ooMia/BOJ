#include <stdio.h>
#include <malloc.h>
#include <string.h>

typedef struct { char isPrime : 1; } BIT;

int main()
{
	int M, N;
	scanf(" %d %d", &M, &N);
	if (N < 2) return 0;
	else if (M <= 2) printf("2\n");

	// 인덱스 번호로 전달된 숫자의 소수 여부를 저장하는 배열
	// (-1: 알 수 없음, 0: 소수가 아님)
	BIT* b = (BIT*)malloc(sizeof(BIT) * N * 2);
	memset(b, -1, sizeof(BIT) * N * 2);
	
	for (int i = 3; i <= N; i+=2)
	{
		if (b[i].isPrime == -1)
		{
			if(M <= i) printf("%d\n", i);
			for (int j = 2; i * j <= N; j++)
				b[i * j].isPrime = 0;
		}
	}

	return 0;
}