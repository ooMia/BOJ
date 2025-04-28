#include <stdio.h>

int main(void)
{
	int X, Y, W, H;
	scanf(" %d %d %d %d", &X, &Y, &W, &H);
	
	int n[] = { X, Y, W - X, H - Y };
	
	for (int i = 1; i < 4; i++)
		if (n[i] < n[0]) n[0] = n[i];

	printf("%d", n[0]);
}