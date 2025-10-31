def solve(n):
    nBag_3 = n // 3
    nBag_5 = 0

    if   n % 3 == 1: nBag_3 -= 3; nBag_5 += 2
    elif n % 3 == 2: nBag_3 -= 1; nBag_5 += 1

    if nBag_3 < 0: return -1

    nBag_5 += (nBag_3 // 5) * 3
    nBag_3 %= 5
    return nBag_3 + nBag_5

print(solve(int(input())))