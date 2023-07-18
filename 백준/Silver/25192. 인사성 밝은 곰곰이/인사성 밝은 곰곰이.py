import sys

IN = list(map(lambda x: x.split()[0], sys.stdin.readlines()[1:]))
res = 0
greeting = set()
for str in IN:
    if str == 'ENTER':
        res += len(greeting)
        greeting.clear()
    else:
        greeting.add(str)
res += len(greeting)
print(res)
