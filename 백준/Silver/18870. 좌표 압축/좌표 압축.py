input()
ls = list(map(int, input().split()))

def compress(nList):
    res = {}
    n_set = sorted(list(set(nList)))
    for i in range(len(n_set)):
        res[n_set[i]] = i
    return res

table = compress(ls)
print(*list(map(lambda x: table[x], ls)))
