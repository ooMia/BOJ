def isValidCoor(node: int):
    return 1 <= node <= nFloors


def getPossibles(visL, baseNode: int):
    dxs = (pos, -neg)

    res = []
    for dx in dxs:
        nextNode = dx + baseNode
        if isValidCoor(nextNode):
            if nextNode not in visL:
                visL.add(nextNode)
                res.append(nextNode)
    return res


def search():
    depth = 0
    visL = {currentFloor}
    curDepth, nextDepth = [currentFloor], []
    while len(curDepth) > 0:
        node = curDepth.pop()
        if node == targetFloor: return depth
        nextDepth.extend(getPossibles(visL, node))

        if len(curDepth) < 1 <= len(nextDepth):
            curDepth = nextDepth.copy()
            nextDepth.clear()
            depth += 1

    return 'use the stairs'


nFloors, currentFloor, targetFloor, pos, neg = map(int, input().split())
print(search())
