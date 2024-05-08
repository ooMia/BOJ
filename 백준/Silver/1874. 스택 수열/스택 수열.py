import sys

arr = list(map(int, sys.stdin.readlines()))

IN = 1
stack = [0]
res = []

for ar in arr[1:]:
    while stack[-1] < ar:
        res.append('+')
        stack.append(IN)
        IN += 1

    if stack[-1] == ar:
        res.append('-')
        stack.pop(-1)
    else:
        break

if IN == arr[0] + 1 and len(stack) == 1:
    print(*res, sep='\n')
else:
    print('NO')
