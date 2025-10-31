import sys


def isVisited(coor: tuple):
    global visM
    return visM[coor[0]][coor[1]]

def addPossibles(df, baseCoor, limit):
    dys = (0, 0, 1, -1)
    dxs = (1, -1, 0, 0)
    for dyx in zip(dys, dxs):
        coor = tuple(map(sum, zip(baseCoor, dyx)))
        if adjM[coor[0]][coor[1]] > limit:
            if not isVisited(coor):
                df.add(coor)

def search(startCoor: tuple):
    global visM
    # outer condition: startCoor is not visited
    Q = set()
    Q.add(startCoor)
    # res = 0
    while len(Q) > 0:
        curCoor = Q.pop()
        visM[curCoor[0]][curCoor[1]] = True
        # res += 1
        addPossibles(Q, curCoor, limit)
    # return res


# return number of connected components in graph adjM (adjacent matrix form)
def solve(limit):
    nConnectedComponents = 0
    global visM
    visM = [[False for c in range(nCols + 2)] for r in range(nRows+ 2)]
    for coor_x in range(1, nCols + 1):
        for coor_y in range(1, nRows + 1):
            coor = (coor_y, coor_x)
            if adjM[coor[0]][coor[1]] > limit:
                if not isVisited(coor):
                    search(coor)
                    nConnectedComponents += 1
    return nConnectedComponents


# 0. fixed-size, non-directional, densible, no invalid cases
nRows = nCols = int(input())

# 1. make graph: adjacency matrix with zero-padding
IN = list(map(lambda x: [0] + list(map(int, x.split())) + [0], sys.stdin.readlines()))
adjM = [[0 for _ in range(nCols + 2)]] + IN + [[0 for _ in range(nCols + 2)]]

limits = set()
for line in IN:
    limits = limits.union(line)

res = []
for limit in limits:
    res.append(solve(limit))
print(max(res))
