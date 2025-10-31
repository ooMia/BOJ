from collections import deque

def search():
    global nRows, nCols, adjM, visM
    Q = deque()

    # initialize
    for r in range(nRows):
        for c in range(nCols):
            ch = adjM[r][c]
            if ch == '@':
                visM[r][c] = True
                Q.appendleft((r, c, 0))
            elif ch == '*':
                visM[r][c] = True
                Q.append((r, c, -1))

    # node: tuple(r: row, c: col, d: depth)
    while len(Q) > 0:
        r, c, depth = Q.pop()

        if depth >= 0 and (r == 0 or r == nRows - 1 or c == 0 or c == nCols - 1):
            return depth + 1

        for dy, dx in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
            ny, nx = r + dy, c + dx
            if 0 <= ny < nRows and 0 <= nx < nCols and not visM[ny][nx]:
                # OK: because '@' and '*' have same action type
                # and fire always moves first
                if adjM[ny][nx] == '.':
                    visM[ny][nx] = True
                    Q.appendleft((ny, nx, depth if depth < 0 else depth + 1))

    return 'IMPOSSIBLE'


for _ in range(int(input())):
    nCols, nRows = map(int, input().split())
    adjM = [input().rstrip() for _ in range(nRows)]
    visM = [[False] * nCols for _ in range(nRows)]
    print(search())
