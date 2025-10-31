#include <stdio.h>
int main() {
	int cur_hour, cur_min, time_min;
	scanf("%d %d", &cur_hour, &cur_min);
	scanf("%d", &time_min);
	cur_min += time_min;
	cur_hour += cur_min / 60, cur_min %= 60;
	cur_hour %= 24;
	printf("%d %d", cur_hour, cur_min);
	return 0;
}