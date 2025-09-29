#include <stdio.h>
int main(void)
{
    int A, B; scanf("%d %d", &A,&B);
    int actual = A*B, pred;
    for(int i=0;i<5;i++){ scanf("%d ", &pred); printf("%d ", pred - actual); }
    return 0;
}