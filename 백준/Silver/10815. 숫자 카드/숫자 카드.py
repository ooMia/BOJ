import sys

own, test = map(lambda x: map(int, x.split()), sys.stdin.readlines()[1::2])
own = set(own)
test = list(test)
print(*map(lambda x:1 if x in own else 0, test))