[a_1, a_0], c, n_0 = [map(int, input().split()), int(input()), int(input())]
cond_1 = (a_1 * n_0 + a_0) <= c * n_0
cond_2 = a_1 <= c
print(1 if cond_1 and cond_2 else 0)
