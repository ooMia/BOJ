import sys


def isVisited(coor: tuple):
    return visM[coor[0]][coor[1]]


def addPossibles(df, baseCoor):
    dys = (0, 0, 1, -1)
    dxs = (1, -1, 0, 0)
    for dyx in zip(dys, dxs):
        coor = tuple(map(sum, zip(baseCoor, dyx)))
        if adjM[coor[0]][coor[1]] == 0:
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
nRows, nCols, nRects = IN[0]

# 0. fixed-size, non-directional, densible, no invalid cases

# 1. make graph: adjacency matrix with one-padding

adjM = [[1 for c in range(nCols + 2)]] + [[1] + [0 for c in range(nCols)] + [1] for r in range(nRows)] + [
    [1 for c in range(nCols + 2)]]
visM = [[False for col in range(1 + nCols + 1)] for row in range(1 + nRows + 1)]

for coor_rect in IN[1:]:
    for coor_x in range(*coor_rect[0:4:2]):
        for coor_y in range(*coor_rect[1:4:2]):
            adjM[coor_y + 1][coor_x + 1] = 1

res = []
for coor_x in range(1, nCols + 1):
    for coor_y in range(1, nRows + 1):
        coor = (coor_y, coor_x)
        if adjM[coor[0]][coor[1]] == 0:
            if not isVisited(coor):
                res.append(search(coor))
print(len(res))
print(*sorted(res), sep='\n')
