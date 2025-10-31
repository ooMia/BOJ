#include <stdio.h>
int main(void)
{
	char tmp[11];
	while (scanf(" %10s", tmp) != EOF) printf("%s\n", tmp);
	return 0;
}