import sys

lines = sys.stdin.readlines()
lines = [line.strip() for line in lines]

A, B, C = map(int, lines)
print(A + B - C)

A, B, C = map(str, lines)
print(int(A + B) - int(C))
