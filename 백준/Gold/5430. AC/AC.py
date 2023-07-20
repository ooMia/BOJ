import sys

N = int(input())
IN = list(map(lambda x: x.rstrip('\n'), sys.stdin.readlines()))
for i in range(0, N * 3, 3):
    OPs, nE, STR = IN[i:i + 3]

    ns = [] if int(nE) <= 0 else list(map(int, STR[1:-1].split(',')))
    idx = 0

    for OP in OPs:
        if OP == 'R':
            idx = idx * -1 - 1
        elif OP == 'D':
            if len(ns) > 0:
                ns.pop(idx)
            else:
                ns = None
                break
        # else:
        #     ns = None
        #     break

    if ns is None:
        print('error')
    else:
        print('[', end='')
        print(*list(reversed(ns)) if idx == -1 else ns, sep=',', end='')
        print(']')
