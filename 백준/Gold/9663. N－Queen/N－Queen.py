def getPossibles(coor: tuple):
    global adjM
    res = []
    r = coor[0] + 1
    for c in range(len(adjM[0])):
        ldru, lurd = r + c, c - r + N - 1
        if adjM[0][c] is False and adjM[1][ldru] is False and adjM[2][lurd] is False:
            res.append((r, c))

    return res


def update_adjL(coor: tuple, value):
    global adjM, N
    r, c = coor[0], coor[1]
    ldru, lurd = r + c, c - r + N - 1
    adjM[0][c] = adjM[1][ldru] = adjM[2][lurd] = value


def search(node: tuple):
    global res
    if node[0] == N - 1:
        res += 1

    for x in getPossibles(node):
        update_adjL(x, True)
        search(x)
        update_adjL(x, False)



nRows = nCols = N = int(input())

adjL_col = [False] * N
adjL_diag_ldru = [False] * (N * 2 - 1)
adjL_diag_lurd = [False] * (N * 2 - 1)
adjM = [adjL_col, adjL_diag_ldru, adjL_diag_lurd]

res = 0
for col in range(nCols):
    update_adjL((0, col), True)
    search((0, col))
    update_adjL((0, col), False)

print(res)
