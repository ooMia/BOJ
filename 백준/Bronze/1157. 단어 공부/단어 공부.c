#include <stdio.h>
#include <malloc.h>
#include <string.h>
int main()
{
	char* S = (char*)malloc(sizeof(char) * 0xFFFFF);
	memset(S, 0, sizeof(char) * 0xFFFFF);
	scanf(" %s", S);

	int cnt[100] = { 0, };
	int nCur, M1, M2;
	char c, c_M;

	nCur = M1 = M2 = 0;
	c = c_M = 'A';

	for (int i = 0;; i++)
	{
		c = S[i];
		if (c == '\0') break;
		else if ('Z' < c) c -= 32;

		nCur = (++cnt[c - 'A']);

		if (nCur > M2) {
			if (nCur > M1) {
				if (c == c_M) M1++;
				else M2 = M1, M1 = nCur, c_M = c;
			}
			else M2 = nCur;
		}
	}

	if (M1 == M2) printf("?");
	else printf("%c", c_M);

	return 0;
}