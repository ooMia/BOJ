#include <stdio.h>
#include <malloc.h>
int main()
{
	int N;
	scanf(" %d", &N);

	int* score = (int*)malloc(sizeof(int) * N);
	int max = -1;
	for (int i = 0; i < N; i++)
	{
		scanf(" %d", &score[i]);
		if (max < score[i]) max = score[i];
	}

	double sum = 0;
	for (int i = 0; i < N; i++)
		sum += (double)score[i] / (double)max * 100.;

	printf("%f", sum / (double)N);
	return 0;
}