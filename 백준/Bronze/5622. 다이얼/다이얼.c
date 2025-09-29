#include <stdio.h>

int main()
{
	short map[256];
	map['A'] = map['B'] = map['C'] = 2 + 1;
	map['D'] = map['E'] = map['F'] = 3 + 1;
	map['G'] = map['H'] = map['I'] = 4 + 1;
	map['J'] = map['K'] = map['L'] = 5 + 1;
	map['M'] = map['N'] = map['O'] = 6 + 1;
	map['P'] = map['Q'] = map['R'] = map['S'] = 7 + 1;
	map['T'] = map['U'] = map['V'] = 8 + 1;
	map['W'] = map['X'] = map['Y'] = map['Z'] = 9 + 1;

	char word[30];
	scanf(" %s", word);

	int time = 0;
	char c;
	for (int i = 0;; i++) {
		c = word[i];
		if (c == '\0') break;
		else time += map[c];
	}
	printf("%d", time);
	return 0;
}