#include <stdio.h>
#include <time.h>
int main(void)
{
    time_t now;
    struct tm * lt;
    
    time(&now);
    lt = gmtime(&now);
    printf ("%d-%d-%d", lt->tm_year+1900, lt->tm_mon+1, lt->tm_mday);
    return 0;
}