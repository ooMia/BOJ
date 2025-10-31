import sys

input = []
for str in sys.stdin.readlines()[1:]:
    input.append(int(str.strip('\n')))

res = []
for n in input:
    calc = []
    for c in (25, 10, 5, 1):
        calc.append(n // c)
        n %= c
    res.append(calc)

for r in res:
    print(*r, sep=' ')
