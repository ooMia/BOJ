import sys


def gcd(a, b):
    while b > 0:
        t = a % b
        a = b
        b = t
    return a


def lcm(a, b):
    return a * b // gcd(a, b)


IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()[1:]))
for pair in IN:
    print(lcm(*sorted(pair, reverse=True)))
    