from collections import deque
from sys import stdin


def search():
    # node: tuple(r:row, c:col, d:depth, w:nWallPassed)
    startNode, destNode = (0, 0, 1, 0), (nRows - 1, nCols - 1, 0, 0)

    visM[startNode[-1]][startNode[0]][startNode[1]] = True
    Q = deque([startNode])

    while len(Q) > 0:
        curNode = Q.popleft()
        if curNode[0] == destNode[0] and curNode[1] == destNode[1]: return curNode[-2]
        # getPossibles

        for dy, dx in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
            nextNode = (curNode[0] + dy, curNode[1] + dx, curNode[2], curNode[3])
            # isValidCoor
            if 0 <= nextNode[0] < nRows and 0 <= nextNode[1] < nCols:
                # isVisited
                if not visM[nextNode[-1]][nextNode[0]][nextNode[1]]:
                    # isValidType
                    isWall = 1 if adjM[nextNode[0]][nextNode[1]] == '1' else 0
                    if nextNode[-1] + isWall < 2:
                        if isWall == 1:
                            visM[0][nextNode[0]][nextNode[1]] = visM[1][nextNode[0]][nextNode[1]] = True
                        visM[nextNode[-1]][nextNode[0]][nextNode[1]] = True
                        Q.append((nextNode[0], nextNode[1], nextNode[2] + 1, nextNode[3] + isWall))
    return -1


nRows, nCols = map(int, stdin.readline().split())
adjM = []
for _ in range(nRows):
    adjM.append(stdin.readline().strip())
visM = [[[False for _ in range(nCols)] for _ in range(nRows)] for _ in range(2)]
print(search())
