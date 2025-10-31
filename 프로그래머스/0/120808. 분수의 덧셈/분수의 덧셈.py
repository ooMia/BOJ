# 두 분수를 더한 값을 기약 분수로 나타냈을 때 분자와 분모
def solution(numer1:int, denom1:int, numer2:int, denom2:int) -> list:
    denom3 = denom1 * denom2
    numer3 = numer1 * denom2 + numer2 * denom1
    gcd3 = gcd(denom3, numer3)
    return [numer3 // gcd3, denom3 // gcd3]


def gcd(a, b):
    while b:
        a, b = b, a % b
    return a