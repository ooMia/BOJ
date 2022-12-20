#define MAX(a,b) (a>b?a:b)
#include <stdio.h>
#include <string.h>
char str1[10000 + 1] = { 0, };
char str2[10000 + 1] = { 0, };
char str3[10000 + 2] = { 0, };
int main() {
	scanf("%s %s", str1, str2);
	int index_1 = strlen(str1) - 1;
	int index_2 = strlen(str2) - 1;
	int index_3 = MAX(index_1, index_2) + 1, index_4 = index_3;
	
	while (index_1 >= 0 && index_2 >= 0)
	{	
		int n1 = str1[index_1--] - '0';
		int n2 = str2[index_2--] - '0';

		str3[index_3--] += n1 + n2;
		if (str3[index_3 + 1] >= 10) { str3[index_3 + 1] -= 10; str3[index_3] += 1; }
	}

	while (index_1 >= 0) {
		str3[index_3--] += str1[index_1--] - '0';
		if (str3[index_3 + 1] >= 10) { str3[index_3 + 1] -= 10; str3[index_3] += 1; }
	}
	while (index_2 >= 0) {
		str3[index_3--] += str2[index_2--] - '0';
		if (str3[index_3 + 1] >= 10) { str3[index_3 + 1] -= 10; str3[index_3] += 1; }
	}
	for (int i = 0; i <= index_4; ++i) str3[i] += '0';

	printf("%s", str3[0] == '0' ? str3 + 1 : str3);
	return 0;
}