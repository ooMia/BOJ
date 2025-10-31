import sys

N, M = map(int, input().split())
IN = list(map(lambda x: x.split()[0], sys.stdin.readlines()))
n, m = set(IN[:N]), set(IN[N:])
res = sorted(list(n.intersection(m)))
print(*[len(res), *res], sep='\n')