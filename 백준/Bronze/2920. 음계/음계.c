#include <stdio.h>
int main()
{
	int A, temp, mode = 0;
	scanf(" %d", &A);
	if (A == 1) mode = +1;
	else if (A == 8) mode = -1;
	else { printf("mixed"); return 0; }

	for (int i = 1; i < 8; i++)
	{
		scanf(" %d", &temp);
		if (A + mode == temp) A = temp;
		else { mode = 0; break; }
	}
	
	switch (mode)
	{
	case +1: printf("ascending"); break;
	case -1: printf("descending"); break;
	default: printf("mixed"); break;
	}

	return 0;
}