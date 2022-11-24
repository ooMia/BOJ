#include <stdio.h>

int isPalindrome(int n)
{
	if (n < 0) return 0;
	else if (n < 10) return 1;

	char m[6];
	int ln = -1;
	while (n > 0)
	{
		m[++ln] = n % 10;
		n /= 10;
	}

	for (int i = 0; i < ln - i; i++)
		if (m[i] != m[ln - i]) return 0;

	return 1;
}

int main()
{
	int N;
	while (1) {
		scanf(" %d", &N);
		if (N == 0) break;
		printf("%s\n", isPalindrome(N) ? "yes" : "no");
	}
	return 0;
}