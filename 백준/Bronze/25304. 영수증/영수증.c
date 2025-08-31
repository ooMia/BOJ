#include <stdio.h>
int main() {
	int X, N, a, b;
	scanf("%d %d", &X, &N);
	for (int n = 0; n < N; ++n) { scanf("%d %d", &a, &b); X -= a * b; }
	printf("%s", X == 0 ? "Yes" : "No");
	return 0;
}