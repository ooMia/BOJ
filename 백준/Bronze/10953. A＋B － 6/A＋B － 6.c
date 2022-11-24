#include <stdio.h>
char str[4];
int main(void)
{
	int T; scanf(" %d", &T);
	for (int t = 0; t < T; ++t) {
		scanf("%s", str);
		printf("%d\n", str[0] + str[2] - '0' * 2);
	}
	return 0;
}