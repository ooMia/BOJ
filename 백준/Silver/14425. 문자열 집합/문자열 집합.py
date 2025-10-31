import sys

N, M = map(int, input().split())
S = list(map(lambda x: x.split()[0], sys.stdin.readlines()))
n, m = set(S[:N]), S[N:N + M]
count = 0
for x in m: count += 1 if x in n else 0
print(count)