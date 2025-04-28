#include <stdio.h>

int isHan(int n)
{
	if (n < 100) return 1;
	else {
		int n1 = n % 10;
		int n2 = (n % 100) / 10;
		int d = n2 - n1;
		
		n /= 100;
		while (n > 0)
		{
			n1 = n2, n2 = n % 10;
			if (n2 != n1 + d) return 0;
			else n /= 10;
		}
		return 1;
	}
}

int main()
{
	int N;
	scanf(" %d", &N);

	int cnt;
	if (N <= 99) cnt = N;
	else {
		cnt = 99;
		for (int i = 111; i <= N; i++)
			if (isHan(i)) cnt++;
	}
	printf("%d", cnt);
	return 0;
}