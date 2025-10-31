#include <malloc.h>
#include <stdio.h> 

int main()
{
	int C;
	scanf(" %d", &C);

	for (int c = 0; c < C; c++)
	{
		int N;
		scanf(" %d", &N);
		
		int avg = 0;
		int* score = (int*)malloc(sizeof(int) * N * 2);

		for (int n = 0; n < N; n++)
		{
			scanf(" %d", &score[n]);
			avg += score[n];
		}
		avg /= N;

		int cnt = 0;
		for (int n = 0; n < N; n++)
			if (avg < score[n]) cnt++;

		printf("%.3f%%\n", (double)cnt / N * 100);
	}
}