import math

def solution(n):
    res = 0
    arr = []
    base = 3 ** int(math.log(n, 3))
    while base > 0:
        arr.append(n // base)
        n %= base
        base //= 3
    
    for i in range(len(arr)):
        res += arr[i] * (3 ** i)
    return res