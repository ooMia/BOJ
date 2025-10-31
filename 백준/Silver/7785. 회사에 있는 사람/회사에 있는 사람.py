import sys

IN = list(map(lambda x: x.split(), sys.stdin.readlines()[1:]))
res = set()
for log in IN:
    if log[1] == 'enter': res.add(log[0])
    else: res.remove(log[0])
print(*sorted(list(res), reverse=True), sep='\n')
