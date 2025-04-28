from sys import stdin
from math import ceil


def main():
    lines = stdin.readlines()
    N = int(lines[0].strip())
    sizes = map(int, lines[1].strip().split())
    T, P = map(int, lines[2].strip().split())
    print(sum(ceil(s / T) for s in sizes))
    print(N // P, N % P)

main()