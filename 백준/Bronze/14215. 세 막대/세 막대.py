import os

In = list(map(int, os.read(0, os.fstat(0).st_size).split()))


def solve(edges):
    edges.sort()
    base = sum(edges[:2])
    if base <= edges[2]: return 2 * base - 1
    else: return base+edges[2]


print(solve(In))
