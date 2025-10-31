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



IN = list(map(lambda x: x.rstrip('\n'), sys.stdin.readlines()))
nRows = nCols = int(IN[0])

# 0. fixed-size, non-directional, densible, no invalid cases

# 1. make graph: adjacency matrix with zero-padding
adjM = ['0' * (1 + nCols + 1)] + list(map(lambda x: '0' + x + '0', IN[1:])) + ['0' * (1 + nCols + 1)]
visM = [[False for col in range(1 + nCols + 1)] for row in range(1 + nRows + 1)]

possibles = set()
for coor_x in range(1, nRows+1):
    for coor_y in range(1, nCols+1):
        if adjM[coor_y][coor_x] == '1':
            possibles.add((coor_y, coor_x))

res = []
for coor in possibles:
    if not isVisited(coor):
        res.append(search(coor))
print(len(res))
print(*sorted(res), sep='\n')
