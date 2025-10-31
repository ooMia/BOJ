import copy
import sys


def isValidCoor(node: tuple):
    global nRows, nCols
    return 0 <= node[0] < nRows and 0 <= node[1] < nCols


def isVisited(visM, node: tuple):
    return visM[node[0]][node[1]]


def isSameType(adjM, nodeA: tuple, nodeB: tuple):
    return adjM[nodeA[0]][nodeA[1]] == adjM[nodeB[0]][nodeB[1]]


def getPossibles(adjM, visM, baseNode: tuple):
    dys = (0, 0, 1, -1)
    dxs = (1, -1, 0, 0)
    res = set()
    for dyx in zip(dys, dxs):
        nextNode = tuple(map(sum, zip(baseNode, dyx)))
        if isValidCoor(nextNode):
            if not isVisited(visM, nextNode):
                if isSameType(adjM, nextNode, baseNode):
                    res.add(nextNode)
    return res


def search(adjM, visM, startNode: tuple):
    Q = {startNode}
    while len(Q) > 0:
        node = Q.pop()
        visM[node[0]][node[1]] = True
        Q = Q.union(getPossibles(adjM, visM, node))
    pass


nRows = nCols = int(input())
adjM_A = IN = list(map(lambda x: [*x.strip()], sys.stdin.readlines()))
adjM_B = copy.deepcopy(adjM_A)
visM_A = [[False for c in range(nCols)] for r in range(nRows)]
visM_B = copy.deepcopy(visM_A)

std_A = {'B': 1, 'G': 2, 'R': 3}
std_B = {'B': 1, 'G': 2, 'R': 2}
for r in range(nRows):
    for c in range(nCols):
        adjM_A[r][c] = std_A[adjM_A[r][c]]
        adjM_B[r][c] = std_B[adjM_B[r][c]]

count_A, count_B = 0, 0
for r in range(nRows):
    for c in range(nCols):
        startNode = (r, c)
        if not isVisited(visM_A, startNode):
            search(adjM_A, visM_A, startNode)
            count_A += 1
        if not isVisited(visM_B, startNode):
            search(adjM_B, visM_B, startNode)
            count_B += 1
print(count_A)
print(count_B)
