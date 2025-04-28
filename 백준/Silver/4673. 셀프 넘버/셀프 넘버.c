#include <stdio.h>
#include <malloc.h>
#include <string.h>

// 셀프 넘버 여부를 저장하는 전역 배열
// (0:알 수 없음, -1:셀프 넘버가 아님)
short isSelf[20000] = { 0, };

int d(int n)
{
	int sum = n;
	while (n > 0)
	{
		sum += n % 10;
		n /= 10;
	}
	return sum;
}

int main()
{
	for (int i = 1; i <= 10000; i++)
	{
		switch (isSelf[i])
		{
		case 0:
			printf("%d\n", i);
			for (int j = d(i); j <= 10000; j = d(j))
			{
				if (isSelf[j] == -1) break;
				else isSelf[j] = -1;
			}
		case -1:
		default:
			break;
		}
	}
	return 0;
}