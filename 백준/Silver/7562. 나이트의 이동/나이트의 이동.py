import sys

isValid = lambda coor: not visM[coor[0]][coor[1]] and adjM[coor[0]][coor[1]]
addTuples = lambda t1, t2: tuple(map(sum, zip(t1, t2)))

def addPossibles(df, baseCoor):
    dys = (+2, +2, +1, +1, -1, -1, -2, -2)
    dxs = (+1, -1, +2, -2, +2, -2, +1, -1)
    for dyx in zip(dys, dxs):
        coor = addTuples(baseCoor, dyx)
        if isValid(coor):
            df.add(coor)


def search(startCoor: tuple, destCoor: tuple):
    timeSpent = 0
    curDepth, nextDepth = set(), set()
    curDepth.add(startCoor)

    while len(curDepth) > 0:
        curCoor = curDepth.pop()
        visM[curCoor[0]][curCoor[1]] = True

        if curCoor == destCoor: return timeSpent
        else: addPossibles(nextDepth, curCoor)

        if len(curDepth) < 1:
            curDepth = nextDepth.copy()
            nextDepth.clear()
            timeSpent += 1
    return -1


IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()[1:]))
for i in range(0, len(IN), 3):
    nCols = nRows = IN[i][0]
    startCoor, destCoor = tuple(IN[i + 1]), tuple(IN[i + 2])

    # double zero-padding
    adjM = [[False for c in range(nCols + 4)] for r in range(2)] + \
           [[False, False] + [True for c in range(nCols)] + [False, False] for r in range(nRows)] + \
           [[False for c in range(nCols + 4)] for r in range(2)]
    visM = [[False for c in range(nCols + 4)] for r in range(nRows + 4)]

    # (+2, +2) for each coordiate caused by double zero-padding
    print(
        search(
            addTuples(startCoor, (+2, +2)),
            addTuples(destCoor, (+2, +2))
        )
    )
