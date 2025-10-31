a_n, a_d = map(int, input().split())
b_n, b_d = map(int, input().split())

def gcd(a, b):
    while b > 0:
        t = a % b
        a = b
        b = t
    return a

d = a_d * b_d
n = a_n * b_d + b_n * a_d
g = gcd(d, n)
print(n//g, d//g)
