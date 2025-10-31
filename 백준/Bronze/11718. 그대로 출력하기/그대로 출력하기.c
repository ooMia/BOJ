#include <stdio.h>
int main(void)
{
	char tmp[101];
	while (scanf(" %[^\n]s", tmp) != EOF) printf("%s\n", tmp);
	return 0;
}