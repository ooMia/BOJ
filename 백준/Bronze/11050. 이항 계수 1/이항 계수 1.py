def comb(n, k):
    if n <= 1 or k == 0 or n == k: return 1
    else: return comb(n - 1, k) + comb(n - 1, k - 1)


N, K = map(int, input().split())
print(comb(N, K))
