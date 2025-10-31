#include <stdio.h>
#include <string.h>
int main()
{
	char cnt['z' - 'a' + 1];
	memset(cnt, -1, sizeof(char) * ('z' - 'a' + 1));

	char S[128];
	scanf(" %s", S);

	char c;
	for (int i = 0;; i++)
	{
		c = S[i];
		if (c == '\0') break;
		else if (cnt[c - 'a'] == -1) cnt[c - 'a'] = i;
	}

	for (int i = 0; i < 'z' - 'a' + 1; i++)
		printf("%d ", cnt[i]);

	return 0;
}