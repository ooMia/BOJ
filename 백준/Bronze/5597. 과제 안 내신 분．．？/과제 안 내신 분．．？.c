#include <stdio.h>
int main() {
	int N = 30, submit[31] = { 0, };
	for (int n = 1; n <= N; ++n) { int id; scanf("%d", &id); ++submit[id]; }

	for (int n = 1; n <= N; ++n) if (submit[n] == 0) printf("%d\n", n);
	return 0;
}