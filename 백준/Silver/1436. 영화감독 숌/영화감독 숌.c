#include <stdio.h>
int main(void)
{
	int N, cnt = 0;
	scanf(" %d", &N);

	int num = 665, nDigit = 2;
	while (cnt < N)
	{
		int n = ++num;

		for (int i = 2, m = 100; n >= m; i++, m *= 10)
			nDigit = i + 1;

		int n6 = 0;
		while (n != 0)
		{
			if (nDigit + n6 < 3) break;

			if (n % 10 == 6) n6++;
			else n6 = 0;

			if (n6 == 3) { cnt++; break; }
			else n /= 10, nDigit--;
		}
	}
	printf("%d\n", num);

	return 0;
}