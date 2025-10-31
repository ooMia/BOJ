#include <stdio.h>
#define SWAP(A,B) do{ int temp=(A); (A)=(B), (B)=temp; }while(0);
#define SQ(N) ((N)*(N))
int main(void)
{
	int edge[3];
	while (1)
	{
		scanf(" %d %d %d", &edge[0], &edge[1], &edge[2]);
		if (!edge[0] && !edge[1] && !edge[2]) break;
		
		for (int i = 1; i <= 2; i++)
			if (edge[0] < edge[i]) SWAP(edge[0], edge[i]);

		int isR = !(SQ(edge[0]) - SQ(edge[1]) - SQ(edge[2]));
		printf("%s\n", (isR) ? "right" : "wrong");
	}
    return 0;
}