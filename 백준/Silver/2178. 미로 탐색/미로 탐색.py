import sys

IN = list(map(lambda x: x.rstrip('\n'), sys.stdin.readlines()))
nRows, nCols = map(int, IN[0].split())

# 0. fixed-size, non-directional, densible, no invalid cases

# 1. make graph: adjacency matrix with zero-padding
adjM = ['0' * (1 + nCols + 1)] + list(map(lambda x: '0' + x + '0', IN[1:])) + ['0' * (1 + nCols + 1)]
isVisited = [[False for col in range(1 + nCols + 1)] for row in range(1 + nRows + 1)]


# 3. add coor within dx, dy
def addPossibles(res: set, base):
    dxs = (1, -1, 0, 0)
    dys = (0, 0, 1, -1)

    for dxy in zip(dxs, dys):
        # Addition of tuples: using map() + zip() + sum()
        coor = tuple(map(sum, zip(base, dxy)))

        if adjM[coor[0]][coor[1]] == '1':
            if not isVisited[coor[0]][coor[1]]:
                res.add(coor)


def bfs(startCoor: tuple, destCoor: tuple):
    # 2-1. init
    depth = 1
    thisDepth, nextDepth = set(), set()
    thisDepth.add(startCoor)

    # 2-1. BFS
    while len(thisDepth) > 0:
        coor = thisDepth.pop()
        isVisited[coor[0]][coor[1]] = True
        if coor == destCoor:
            return depth

        addPossibles(nextDepth, coor)
        if len(thisDepth) < 1:
            thisDepth = nextDepth.copy()
            nextDepth.clear()
            depth += 1
            # print(f'depth {depth}: {thisDepth}')

    # if no valid path exists
    return -1


print(bfs((1, 1), (nRows, nCols)))
