#include <stdio.h>
#include <malloc.h>

int N, M;
char* board;
char chessboard[2][8][9] =
{
	{
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB"
	},{
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW",
		"WBWBWBWB",
		"BWBWBWBW"
	}
};

int countCorr(int n, int m)
{
	int res[2] = { 0, };
	for (int type = 0; type < 2; type++) {
		for (int h = 0; h < 8; h++)
			for (int w = 0; w < 8; w++)
				if (board[(n * M + m) + (h * M + w)] != chessboard[type][h][w]) res[type]++;
	}
	return (res[0] < res[1]) ? res[0] : res[1];
}

int main(void)
{
	scanf(" %d %d", &N, &M);
	
	board = (char*)malloc(sizeof(char) * N * M * 2);
	
	for (int n = 0; n < N; n++)
		scanf(" %s", &board[n * M]);

	int min = N * M, res;

	for (int n = 0; n + 8 <= N; n++)
		for (int m = 0; m + 8 <= M; m++) {
			res = countCorr(n, m);
			if (res < min) min = res;
		}

	printf("%d", min);
	return 0;
}