#include <stdio.h>
int main()
{
	int T; 
	scanf(" %d", &T);

	for (int t = 0; t < T; t++)
	{
		char S[80];
		scanf(" %s", S);
		
		int score = 0, streak = 0;
		char c;
		for (int i = 0;; i++)
		{
			c = S[i];
			if (c == '\0') break;
			else if (c == 'O') score += ++streak;
			else streak = 0;
		}
		printf("%d\n", score);
	}

	return 0;
}