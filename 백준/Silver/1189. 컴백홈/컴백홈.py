import copy
import sys

addTuples = lambda t1, t2: tuple(map(sum, zip(t1, t2)))
isValidCoor = lambda coor: 0 <= coor[0] < nRows and 0 <= coor[1] < nCols and adjM[coor[0]][coor[1]] != 'T'


def getPossibles(baseCoor, visM):
    df = set()
    dys = (1, -1, 0, 0)
    dxs = (0, 0, 1, -1)
    for dyx in zip(dys, dxs):
        coor = addTuples(baseCoor, dyx)
        if isValidCoor(coor) and not visM[coor[0]][coor[1]]:
            df.add(coor)
    return df



def search(curCoor, visM, depth):
    visM[curCoor[0]][curCoor[1]] = True
    depth += 1
    if depth == maxDepth:
        return 1 if curCoor == destCoor else 0
    else:
        return sum(list(map(lambda x: search(x, copy.deepcopy(visM), depth), getPossibles(curCoor, visM))))

nRows, nCols, maxDepth = map(int, input().split())
adjM = IN = list(map(lambda x: [*x.strip()], sys.stdin.readlines()))
visM = [[False for c in range(nCols)] for r in range(nRows)]
startCoor, destCoor = (nRows - 1, 0), (0, nCols - 1)
print(search(startCoor, visM, 0))
