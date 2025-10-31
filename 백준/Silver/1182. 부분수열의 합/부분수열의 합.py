import sys


def search(nList, pSum):
    if len(nList) < 1:
        return 1 if pSum == target else 0
    return search(nList[1:], pSum + nList[0]) + search(nList[1:], pSum)


IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()))
N, target = IN[0]
res = search(IN[1], 0) - (1 if target == 0 else 0)
print(res)
