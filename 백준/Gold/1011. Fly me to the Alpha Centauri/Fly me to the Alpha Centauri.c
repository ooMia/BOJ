#include <stdio.h>

int countWarp(int dist)
{
	if (dist <= 3) return dist;
	
	int limit = 1, cnt = 0;
	while (dist != 0)
	{
		if (limit * 2 <= dist) dist -= limit * 2, limit++, cnt += 2;
		else if (limit <= dist) dist -= limit, cnt++;
		else limit--;
	}
	return cnt;
}

int main(void)
{
	int T, X, Y;
	scanf(" %d", &T);
	for (int t = 0; t < T; t++)
	{
		scanf(" %d %d", &X, &Y);
		printf("%d\n", countWarp(Y - X));
	}
}