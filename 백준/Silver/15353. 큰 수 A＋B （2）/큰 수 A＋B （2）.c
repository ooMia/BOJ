#include <stdio.h>
#include <string.h>
#include <malloc.h>

char* add_integerString(const char* num1, const char* num2)
{
	const int len_num1 = (int)strlen(num1);
	const int len_num2 = (int)strlen(num2);
	const int len_num3 = (len_num1 > len_num2 ? len_num1 : len_num2) + 1;

	char* num3 = (char*)malloc((size_t)len_num3 + 1);
	if (num3 != NULL) memset(num3, 0, (size_t)len_num3 + 1);
	else return NULL;

	// num1, num2, num3 = {0, }

	char* ptr_num3_1 = num3 + len_num3 - 1;
	for (int i1 = len_num1 - 1; i1 >= 0; --i1) *ptr_num3_1-- += num1[i1] - '0';

	char* ptr_num3_2 = num3 + len_num3 - 1;
	for (int i2 = len_num2 - 1; i2 >= 0; --i2) *ptr_num3_2-- += num2[i2] - '0';

	for (int i3 = len_num3 - 1; i3 >= 0; --i3) {
		if (num3[i3] >= 10) num3[i3] -= 10, num3[i3 - 1] += 1;
		num3[i3] += '0';
	}

	while (*num3 == '0') ++num3;
	return num3;
}

char g_str1[10000 + 1] = { 0, };
char g_str2[10000 + 1] = { 0, };

int main() {
	scanf("%s %s", g_str1, g_str2);
	printf("%s", add_integerString(g_str1, g_str2));
	return 0;
}