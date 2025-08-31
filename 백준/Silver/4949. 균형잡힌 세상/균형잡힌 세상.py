import sys

lines = map(lambda x: x.rstrip('.\n'), sys.stdin.readlines()[:-1])
target = {'in': '[(', 'out': '])'}


for line in lines:
    res = True
    stack = []
    for ch in line:
        if ch in target['in']:
            stack.append(ch)
        elif ch in target['out']:
            if not (len(stack) > 0 and target['in'].find(stack.pop(-1)) == target['out'].find(ch)):
                res = False
                break
    print('yes' if res and len(stack) == 0 else 'no')
    