#include <stdio.h>
int main()
{
	int burger = 0xFFF, beverage = 0xFFF, temp;

	for (int i = 0; i < 3; i++)
	{
		scanf(" %d", &temp);
		if (temp < burger) burger = temp;
	}
	for (int i = 0; i < 2; i++)
	{
		scanf(" %d", &temp);
		if (temp < beverage) beverage = temp;
	}
	printf("%d", burger + beverage - 50);
	return 0;
}