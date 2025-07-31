import sys

N, M = map(int, input().split())
es = list(map(lambda x: x.rstrip('\n'), sys.stdin.readlines()))

table = dict()
for e in es:
    if e in table.keys():
        table[e] += 1
    elif len(e) >= M:
        table[e] = 1

res = list(table.keys())
print(*sorted(sorted(res), key=lambda x: (table[x], len(x)), reverse=True), sep='\n')
