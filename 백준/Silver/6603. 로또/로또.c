#include <stdio.h>

void printCombinations(int* set, int setSize)
{
	for (int n1 = 0; n1 < setSize - 5; n1++)
		for (int n2 = n1 + 1; n2 < setSize - 4; n2++)
			for (int n3 = n2 + 1; n3 < setSize - 3; n3++)
				for (int n4 = n3 + 1; n4 < setSize - 2; n4++)
					for (int n5 = n4 + 1; n5 < setSize - 1; n5++)
						for (int n6 = n5 + 1; n6 < setSize; n6++)
							printf("%d %d %d %d %d %d\n", set[n1], set[n2], set[n3], set[n4], set[n5], set[n6]);
}

int main(void)
{
	int k;
	int S[12];

	while (1)
	{
		scanf(" %d", &k);
		if (k == 0) break;

		for (int i = 0; i < k; i++)
			scanf(" %d", S + i);

		printCombinations(S, k);
		puts("");
	}

	return 0;
}