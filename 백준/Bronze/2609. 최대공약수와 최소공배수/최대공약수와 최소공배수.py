from math import gcd, lcm
from sys import stdin

line = stdin.readline()
A, B = map(int, line.split())
print(gcd(A, B))
print(lcm(A, B))
