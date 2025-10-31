from functools import reduce

N, M = map(int, input().split())

es = list(map(int, input().split()))
es.append(1)
res = reduce(lambda x, y: ((x % M) * (y % M)) % M, es)
print(res)
