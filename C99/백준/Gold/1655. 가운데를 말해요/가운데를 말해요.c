#include <stdio.h>
#include <malloc.h>

typedef struct LinkedListNode {
	int val, cnt;
	struct LinkedListNode* L, * R;
} Node;

Node* head;
Node* dir[20001] = { 0, };
#define DIR(N) (dir[10000+(N)])
int median, limit, pSum = 1;

Node* createNode(int newNum)
{
	Node* res = DIR(newNum) = (Node*)malloc(sizeof(Node));
	res->val = newNum, res->cnt = 1, res->L = res->R = NULL;

	Node* cur;
	for (int i = newNum - 1; i >= -10000; --i) {
		cur = DIR(i);
		if (cur != NULL) { res->L = cur, cur->R = res; break; }
	}
	for (int j = newNum + 1; j <= +10000; ++j) {
		cur = DIR(j);
		if (cur != NULL) { res->R = cur, cur->L = res; break; }
	}
	return res;
}

void push(int num)
{
	Node* res = DIR(num);
	if (res == NULL) createNode(num);
	else ++res->cnt;
}

int getMedian()
{
	if (pSum > limit)
		if (pSum - head->cnt >= limit) pSum -= head->cnt, head = head->L;
	if (pSum < limit) head = head->R, pSum += head->cnt;
	return median = head->val;
}

int main(void)
{
	int maxN, num;
	scanf(" %d %d", &maxN, &num);
	printf("%d\n", median = num);
	head = createNode(num);

	for (int N = 2; N <= maxN; ++N) {
		scanf(" %d", &num); push(num);
		limit = (N + 1) / 2;
		if (num <= median) ++pSum;
		printf("%d\n", getMedian());
	}
	return 0;
}