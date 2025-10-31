def solve(a, b, c, d, e, f):
    f_ = lambda x, y: a * x + b * y - c
    g_ = lambda x, y: d * x + e * y - f
    for x in range(-999, 1000):
        for y in range(-999, 1000):
            if f_(x, y) == 0 and g_(x, y) == 0: return x, y

args = map(int, input().split())
print(*solve(*args), sep=' ')
