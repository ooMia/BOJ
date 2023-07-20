import sys

arr = list(map(int, sys.stdin.readlines()))

IN = [n for n in range(1, arr[0] + 1)]
stack = [0]
res = []

for ar in arr[1:]:
    while stack[-1] < ar:
        res.append('+')
        stack.append(IN.pop(0))

    if stack[-1] == ar:
        res.append('-')
        stack.pop(-1)
    else:
        break

if len(IN) == 0 and len(stack) == 1:
    print(*res, sep='\n')
else:
    print('NO')
