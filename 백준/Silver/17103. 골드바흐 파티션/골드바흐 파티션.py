# 1,000,000까지 isPrime 배열을 생성
import math
import sys

nLimit = 1000 * 1000
isPrime_arr: [bool] = [True for _ in range(nLimit + 1)]


def isPrime(n):
    if n == 2: return True
    elif n < 2 or n % 2 == 0: return False
    else: return isPrime_arr[n]


for step in range(3, math.isqrt(nLimit) + 1):
    if not isPrime(step):
        continue
    for i in range(step * 2, len(isPrime_arr), step):
        isPrime_arr[i] = False

def solve(n):
    res = set()
    if isPrime(n - 2): res.add((2, n - 2))
    for p in range(3, n // 2 + 1, 2):
        if isPrime(n - p) and isPrime(p): res.add((p, n - p))
    return len(list(res))

IN = list(map(int, sys.stdin.readlines()[1:]))
print(*list(map(solve, IN)), sep='\n')