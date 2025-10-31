#include <stdio.h>
#include <malloc.h>

typedef unsigned long long ull;

ull* res, * temp;

ull* mergeSort(ull* arr, int len)
{
	if (len < 2) return arr;

	int lenA = len / 2, lenB = len - lenA;
	ull* arrA = mergeSort(arr, lenA);
	ull* arrB = mergeSort(arr + lenA, lenB);

	// 각각 정렬된 arrA, arrB 배열을 temp 하나에 재정렬
	int iTemp = 0, iA = 0, iB = 0;
	while (iA < lenA && iB < lenB) {
		if (arrA[iA] < arrB[iB]) temp[iTemp++] = arrA[iA++];
		else temp[iTemp++] = arrB[iB++];
	}
	while (iA < lenA) temp[iTemp++] = arrA[iA++];
	while (iB < lenB) temp[iTemp++] = arrB[iB++];

	// res = arrA + arrB
	for (int iRes = 0; iRes < lenA + lenB; iRes++)
		res[arr - res + iRes] = temp[iRes];

	return arr;
}

int main() {
	int N, x, y; scanf(" %d", &N);
	ull bits = 8 * sizeof(int);

	res = (ull*)malloc(sizeof(ull) * N);
	temp = (ull*)malloc(sizeof(ull) * N);

	for (int i = 0; i < N; i++) {
		scanf(" %d %d", &x, &y);
		res[i] = x + 100000; res[i] <<= bits; res[i] += y + 100000;
	}

	mergeSort(res, N);

	for (int i = 0; i < N; i++)
		printf("%d %d\n", (res[i] >> bits) - 100000, res[i] - 100000);

	free(temp), free(res);
	return 0;
}