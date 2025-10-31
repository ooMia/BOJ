import math
from collections import deque


def search():
    visL.add(startNode)
    # node: tuple(n:num, d:depth)
    Q = deque([(startNode, 0)])

    while len(Q) > 0:
        nextNode = Q.popleft()
        if nextNode[-1] > maxDepth: break
        elif nextNode[0] == destNode: return nextNode[-1]

        buttonA = lambda x: x + 1
        if nextNode[0] < 99999:
            val = buttonA(nextNode[0])
            if val not in visL:
                visL.add(val)
                Q.append((val, nextNode[-1] + 1))

        buttonB = lambda x: x * 2 - 10 ** int(math.log10(x * 2))
        if 0 < nextNode[0] < 50000:
            val = buttonB(nextNode[0])
            if val not in visL:
                visL.add(val)
                Q.append((val, nextNode[-1] + 1))

    return 'ANG'


startNode, maxDepth, destNode = map(int, input().split())
visL = set()
print(search())
