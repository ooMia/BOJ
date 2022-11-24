#include <malloc.h>
#include <stdio.h>
int main()
{
	int cnt = 0;
	char* S = (char*)malloc(sizeof(char) * 0xFFFFF);
	for (int i = 0;; i++)
	{
		if (scanf(" %s", S) == EOF) break;
		else cnt++;
	}
	printf("%d", cnt);

	return 0;
}