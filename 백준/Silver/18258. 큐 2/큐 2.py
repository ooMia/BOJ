from collections import deque
import sys

INs = list(map(lambda x: x.split(), sys.stdin.readlines()[1:]))
Q = deque()

for IN in INs:
    cmd = IN[0]
    if cmd == 'push':
        Q.append(IN[1])
    elif cmd == 'pop':
        print(-1 if len(Q) < 1 else Q.popleft())
    elif cmd == 'size':
        print(len(Q))
    elif cmd == 'empty':
        print(1 if len(Q) < 1 else 0)
    elif cmd == 'front':
        print(-1 if len(Q) < 1 else Q[0])
    elif cmd == 'back':
        print(-1 if len(Q) < 1 else Q[-1])
