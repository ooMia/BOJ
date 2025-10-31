#include <stdio.h>
int main() {
	int N, n_list[100], v;
	scanf("%d", &N);
	for (int n = 0; n < N; ++n) { scanf("%d", n_list + n); }
	scanf("%d", &v);

	int count = 0;
	for (int n = 0; n < N; ++n) if (n_list[n] == v) ++count;
	printf("%d", count);
	return 0;
}