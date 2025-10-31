import sys

def isValidCoor(node: tuple):
    global nFloors, nRows, nCols
    return 0 <= node[0] < nFloors and 0 <= node[1] < nRows and 0 <= node[2] < nCols


def isVisited(visM, node: tuple):
    return visM[node[0]][node[1]][node[2]]

def isValidType(adjM, node: tuple):
    global keySet
    return adjM[node[0]][node[1]][node[2]] > keySet['#']

def getPossibles(adjM, visM, baseNode: tuple):
    dzs = (1, -1, 0, 0, 0, 0)
    dys = (0, 0, 1, -1, 0, 0)
    dxs = (0, 0, 0, 0, 1, -1)

    res = []
    for dzyx in zip(dzs, dys, dxs):
        nextNode = tuple(map(sum, zip(baseNode, dzyx)))
        if isValidCoor(nextNode):
            if not isVisited(visM, nextNode):
                if isValidType(adjM, nextNode):
                    visM[nextNode[0]][nextNode[1]][nextNode[2]] = True
                    res.append(nextNode)
    return res


def search(adjM, visM, startNode: tuple, destNode: tuple):
    visM[startNode[0]][startNode[1]][startNode[2]] = True
    curDepth, nextDepth = [startNode], []
    depth = 0

    while len(curDepth) > 0:
        node = curDepth.pop()
        if node == destNode: return depth
        else: nextDepth.extend(getPossibles(adjM, visM, node))

        if len(curDepth) < 1:
            curDepth = nextDepth.copy()
            nextDepth.clear()
            depth += 1
    return -1


while True:
    IN = list(map(int, sys.stdin.readline().split()))
    if min(IN) < 1: break
    nFloors, nRows, nCols = IN

    adjM = []
    for f in range(nFloors):
        adjM.append([])
        for r in range(nRows):
            IN = [*sys.stdin.readline().strip()]
            adjM[f].append(IN)
        sys.stdin.readline()
    visM = [[[False for c in range(nCols)] for r in range(nRows)] for f in range(nFloors)]

    startNode, destNode = tuple(), tuple()
    keySet = {'#': 0, '.': 1, 'S': 1, 'E': 1}
    for f in range(nFloors):
        for r in range(nRows):
            for c in range(nCols):
                ch = adjM[f][r][c]
                if ch == 'S': startNode = (f, r, c)
                if ch == 'E': destNode = (f, r, c)
                adjM[f][r][c] = keySet[ch]

    timeTaken = search(adjM, visM, startNode, destNode)
    if timeTaken > 0:
        print(f'Escaped in {timeTaken} minute(s).')
    else:
        print('Trapped!')
