def gcd(a, b):
    while b > 0:
        t = a % b
        a = b
        b = t
    return a


def lcm(a, b):
    return a // gcd(a, b) * b

a, b = map(int, input().split())
print(lcm(a, b))