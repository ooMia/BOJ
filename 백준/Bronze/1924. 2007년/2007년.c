#include <stdio.h>
int days[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
char* res[] = { "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN" };
int main(void)
{
	int M, D; scanf(" %d %d", &M, &D);
	int sum = D - 1; for (int m = 1; m < M; ++m) sum += days[m];
	printf("%s", res[sum % 7]);
	return 0;
}