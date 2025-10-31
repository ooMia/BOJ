import sys

es = map(int, sys.stdin.readlines()[1:])
res = list()
for e in es:
    if e == 0: res.pop()
    else: res.append(e)
print(sum(res))