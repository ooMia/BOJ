import sys

es = list(map(lambda s: list(map(int, s.split())), sys.stdin.readlines()[1:]))
es.sort(key=lambda xy: (xy[1], xy[0]))
for e in es:
    print(*e)