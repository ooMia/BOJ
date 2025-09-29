import sys

N, M = map(int, input().split())
IN = list(map(lambda x: x.split()[0], sys.stdin.readlines()))
n, m = IN[:N], IN[N:]

n.insert(0, None)
table = {}
for i in range(len(n)):
    table[n[i]] = i

for quiz in m:
    try:
        print(n[int(quiz)])
    except ValueError:
        print(table[quiz])
