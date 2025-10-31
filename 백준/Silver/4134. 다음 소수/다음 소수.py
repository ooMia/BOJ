import math
import sys


def isPrime(n):
    if n < 2: return False
    if n == 2: return True
    for i in range(2, math.isqrt(n) + 1):
        if n % i == 0: return False
    return True


IN = list(map(lambda x: int(x.split()[0]), sys.stdin.readlines()[1:]))
for n_from in IN:
    p_cand = 2 if n_from <= 2 else n_from + ((n_from + 1) % 2)
    while not isPrime(p_cand):
        p_cand += 2
    print(p_cand)
    