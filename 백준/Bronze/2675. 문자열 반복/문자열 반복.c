#include <stdio.h>
int main()
{
	int T;
	scanf(" %d", &T);

	for (int t = 0; t < T; t++)
	{
		int R; char S[20];
		scanf(" %d %s", &R, &S);
		char c;
		for (int i = 0;; i++)
		{
			c = S[i];
			if (c == '\0') break;
			else for (int r = 0; r < R; r++) printf("%c", c);
		}
		printf("\n");
	}

	return 0;
}