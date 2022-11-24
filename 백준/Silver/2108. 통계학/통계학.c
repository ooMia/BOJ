#include <stdio.h>

#define count(N) (CNT[4000+(N)])

int sum, cumul_count, median_count;
short min, max, median;
short mode[3] = { 0, };
int CNT[8001] = { 0, };

void calcMode(short num) {
	short M; int cM;
	int cNum = ++count(num);

	for (char i = 0; i <= 1; i++) {
		M = mode[i];
		if (num == M) break; else cM = count(M);
		if (cNum > cM || (cNum == cM && num < M)) {
			mode[i] = num, mode[i + 1] = M; break;
		}
	}
}

short calcAvg(int N, int sum) {
	int L = sum / N;
	double R = (double)sum / N - L;
	char sign = (L < 0) ? -1 : +1;

	return (short)(L + sign * (0.5 <= sign * R));
}

int main(void)
{
	int N; short in[2]; scanf(" %d %hd", &N, in);
	if (N == 1) { printf("%hd\n%hd\n%hd\n0", *in, *in, *in); return 0; }

	sum = min = max = mode[0] = *in, ++count(*in);

	for (int iter = 1; iter < N; iter += 2) {
		scanf(" %hd %hd", in, in + 1);

		for (char i = 0; i < 2; ++i) {
			short s = in[i];

			sum += s;
			if (s < min) min = s; else if (s > max) max = s;
			calcMode(s);
		}
	}
	median = min - 1, cumul_count = 0, median_count = (N + 1) / 2;
	while (cumul_count < median_count) cumul_count += count(++median);

	printf("%hd\n%hd\n%hd\n%hd", calcAvg(N,sum), median, (count(mode[0]) == count(mode[1])) ? mode[1] : mode[0], max - min);
	return 0;
}