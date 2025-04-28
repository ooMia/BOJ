import sys

ls = sorted(list(map(int, sys.stdin.readlines()[1:][0].split())))
print(ls[0] * ls[-1])