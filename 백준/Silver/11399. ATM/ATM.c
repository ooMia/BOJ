#include <stdio.h>
#include <stdlib.h>

// qsort 비교함수
int static compare (const void* first, const void* second)
{
    if (*(int*)first > *(int*)second)
        return 1;
    else if (*(int*)first < *(int*)second)
        return -1;
    else
        return 0;
}

int main()
{
    // input
    int N; scanf(" %d", &N); // 총 인원 수
    int* P = (int*)malloc(sizeof(int) * N); // 개인별 소요 시간
    for (int i=0; i<N; ++i) scanf(" %d", P+i);

    // P배열 오름차순 정렬
    qsort(P, N, sizeof(int), compare);
    
    int res = 0; // 누적 시간
    for (int i=0; N>0; ++i) res += P[i] * N--;
    
    // output
    printf("%d\n", res);
    
    return 0;
}