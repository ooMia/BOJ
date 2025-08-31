import sys

ns = []
for s in sys.stdin.readlines()[1:]:
    ns.append(int(s.split('\n')[0]))
print(*sorted(ns), sep='\n')
