import sys

def isValidCoor(node: tuple):
    global nRows, nCols
    return 0 <= node[0] < nRows and 0 <= node[1] < nCols


def isVisited(visM, node: tuple):
    return visM[node[0]][node[1]]

def isValidType(adjM, node: tuple):
    return adjM[node[0]][node[1]] == 0

def getPossibles(adjM, visM, baseNode: tuple):
    dys = (1, -1, 0, 0)
    dxs = (0, 0, 1, -1)

    res = []
    for dyx in zip(dys, dxs):
        nextNode = tuple(map(sum, zip(baseNode, dyx)))
        if isValidCoor(nextNode):
            if not isVisited(visM, nextNode):
                if isValidType(adjM, nextNode):
                    adjM[nextNode[0]][nextNode[1]] = 1
                    visM[nextNode[0]][nextNode[1]] = True
                    res.append(nextNode)
    return res



def search(adjM, visM, startNodes: list):
    curDepth, nextDepth = startNodes, []
    depth = 0

    while len(curDepth) > 0:
        node = curDepth.pop()
        nextDepth.extend(getPossibles(adjM, visM, node))

        if len(curDepth) < 1 <= len(nextDepth):
            curDepth = nextDepth.copy()
            nextDepth.clear()
            depth += 1

    return depth



nCols, nRows = map(int, input().split())
adjM = IN = list(map(lambda x: list(map(int, [*x.split()])), sys.stdin.readlines()))
visM = [[False for c in range(nCols)] for r in range(nRows)]

startNodes = []
for r in range(nRows):
    for c in range(nCols):
        if adjM[r][c] > 0:
            visM[r][c] = True
            startNodes.append((r, c))
res = search(adjM, visM, startNodes)
for r in range(nRows):
    for c in range(nCols):
        if adjM[r][c] == 0:
            res = -1
            break
print(res)
