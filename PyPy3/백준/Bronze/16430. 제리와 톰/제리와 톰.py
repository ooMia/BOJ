import math
[a,b] = map(int, input().split())
a = b-a
g = math.gcd(a, b)
print(a//g, b//g)