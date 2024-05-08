#include <stdio.h>
#include <string.h>
int main() {
	int N, count;
	scanf("%d", &N); count = N;

	for (int n = 0; n < N; ++n)
	{
		char str[101] = { 0, }; scanf("%s", str); // lower-case alphabet
		int n_str = strlen(str);
		char check['z' + 1] = { 0, };

		for (int i = 0; i < n_str; ++i)
		{
			char c = str[i];
			
			// 나왔던 문자인지 확인
			if (check[c] == 1) { --count; break; }

			// 안 나왔으면 다른 문자 나올 때까지 while로 넘기기
			while (str[++i] == c); --i; 			
			check[c] = 1;
		}

	}
	printf("%d", count);
	return 0;
}