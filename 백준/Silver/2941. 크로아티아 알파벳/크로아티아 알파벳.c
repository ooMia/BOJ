#include <stdio.h>
#include <string.h>
int main() {
	char str[101]; scanf("%s", str); // lower-case alphabet & '-' & '='
	
	int n_str = strlen(str), count = n_str;
	for (int i = 0; i < n_str; ++i) {
		switch (str[i])
		{
		case '=':
			if (i >= 2 && strncmp(str + i - 2, "dz=", 3) == 0) count -= 2;
			else if (i >= 1 && strncmp(str + i - 1, "c=", 2) == 0) count -= 1;
			else if (i >= 1 && strncmp(str + i - 1, "s=", 2) == 0) count -= 1;
			else if (i >= 1 && strncmp(str + i - 1, "z=", 2) == 0) count -= 1;
			break;
		case '-':
			if (i >= 1 && strncmp(str + i - 1, "c-", 2) == 0) count -= 1;
			else if (i >= 1 && strncmp(str + i - 1, "d-", 2) == 0) count -= 1;
		case 'j':
			if (i >= 1 && strncmp(str + i - 1, "lj", 2) == 0) count -= 1;
			else if (i >= 1 && strncmp(str + i - 1, "nj", 2) == 0) count -= 1;
		default:
			break;
		}
	}
	printf("%d", count);
	return 0;
}