import collections


def addPossibles(df, base):
    dxs = [base + 1, base - 1, base * 2]
    for s in dxs:
        if 0 <= s <= limit:
            global visS
            if s not in visS:
                df.append(s)


def search(curCoor, destCoor):
    timeSpent = 0
    curDepth = collections.deque([curCoor])
    nextDepth = collections.deque()

    while len(curDepth) > 0:
        curCoor = curDepth.popleft()
        if curCoor == destCoor: return timeSpent

        global visS
        visS.add(curCoor)
        addPossibles(nextDepth, curCoor)

        if len(curDepth) < 1:
            curDepth = nextDepth.copy()
            nextDepth.clear()
            timeSpent += 1
    return -1


limit = 100000
curCoor, destCoor = N, K = IN = list(map(int, input().split()))
visS = set()
res = search(curCoor, destCoor)
print(res)
