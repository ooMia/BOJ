import sys


def isVisited(coor: tuple):
    return visM[coor[0]][coor[1]]


def addPossibles(df, baseCoor):
    dys = (0, 0, 1, -1)
    dxs = (1, -1, 0, 0)
    for dyx in zip(dys, dxs):
        coor = tuple(map(sum, zip(baseCoor, dyx)))
        if coor in possibles:
            if not isVisited(coor):
                df.add(coor)


def search(startCoor: tuple):
    # outer condition: startCoor is not visited
    Q = set()
    Q.add(startCoor)
    res = 0
    while len(Q) > 0:
        curCoor = Q.pop()
        visM[curCoor[0]][curCoor[1]] = True
        res += 1
        addPossibles(Q, curCoor)
    return res


IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()))
nRows, nCols, nNodes = IN[0]

# 0. fixed-size, non-directional, densible
# 1. make graph: adjacency matrix with zero-padding
adjM = [[0 for _ in range(nCols + 2)] for _ in range(nRows + 2)]
visM = [[False for _ in range(nCols + 2)] for _ in range(nRows + 2)]

# 1-1. make set of nodes & update graph status
possibles = set()
for coor in IN[1:]:
    adjM[coor[0]][coor[1]] = 1
    possibles.add(tuple(coor))

res = []
for coor in possibles:
    if not isVisited(coor):
        res.append(search(coor))
print(max(res))
