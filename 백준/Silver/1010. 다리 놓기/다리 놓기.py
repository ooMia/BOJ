import math

for _ in range(int(input())):
    N, M = map(int, input().split())
    print(math.comb(M, N))
