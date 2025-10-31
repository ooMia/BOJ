#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main() {
	int map[7] = { 0, }, res = 0;
	int dice, n_dice = 3;
	for (int i = 0; i < n_dice; ++i)
		scanf("%d", &dice), ++map[dice];

	// 같은 눈이 존재하는 경우
	for (int i = 1; i <= 6; ++i) {
		if (map[i] == 3) { res = 10000 + i * 1000; break; }
		else if (map[i] == 2) { res = 1000 + i * 100; break; }
	}
	if (res == 0) {
		// 모두 다른 눈인 경우
		for (int i = 6; i > 0; --i) {
			if (map[i] == 1) { res = i * 100; break; }
		}
	}
	printf("%d", res);
	return 0;
}