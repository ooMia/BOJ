#include <stdio.h>
int main(void)
{
    int temp, ans[6] = {1,1,2,2,2,8};
    for(int i=0; i<6; i++) scanf("%d ", &temp), printf("%d ", ans[i]-temp);
    return 0;
}