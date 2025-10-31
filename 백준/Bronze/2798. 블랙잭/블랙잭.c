#include <stdio.h>
int calcMax(int* arr, int arrSize, int limit) {
	int max = 0;
	for (int i1 = 0; i1 < arrSize - 2; i1++)
		for (int i2 = i1 + 1; i2 < arrSize - 1; i2++)
			for (int sum, i3 = i2 + 1; i3 < arrSize - 0; i3++)
				if ((sum = arr[i1] + arr[i2] + arr[i3]) <= limit && max < sum) max = sum;
	return max;
}
int main() {
	int N, M, sum = 0;
	scanf(" %d %d", &N, &M);

	int arr[100];
	for (int n = 0; n < N; n++) scanf(" %d", arr + n);
	printf("%d", calcMax(arr, N, M));

	return 0;
}