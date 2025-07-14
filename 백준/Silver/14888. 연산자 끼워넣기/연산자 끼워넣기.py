import sys
from functools import reduce

def getCalc(iOP):
    calc = lambda x, y: x + y
    if iOP == 1: calc = lambda x, y: x - y
    elif iOP == 2: calc = lambda x, y: x * y
    elif iOP == 3: calc = lambda x, y: int(x/y)
    return calc

def search(nList, OPs, partialSum):
    nZerosOP = 0
    for i in range(len(OPs)):
        if OPs[i] == 0: nZerosOP += 1
    if nZerosOP >= 4: return {partialSum}

    # have a single type of operation to choose
    iOP = 3
    while OPs[iOP] < 1: iOP -= 1
    if nZerosOP == 3:
        return {reduce(getCalc(iOP), [partialSum] + nList)}

    res = set()
    for iOP in range(len(OPs)):
        if OPs[iOP] < 1: continue
        new_OPs = OPs.copy()
        new_OPs[iOP] -= 1
        calc = getCalc(iOP)
        res = res.union(search(nList[1:], new_OPs, calc(partialSum, nList[0])))
    return res



IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()[1:]))
nList, OPs = IN
res = search(nList[1:], OPs, nList[0])
print(max(res))
print(min(res))
