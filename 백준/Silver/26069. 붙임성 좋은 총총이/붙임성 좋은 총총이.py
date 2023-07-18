import sys

IN = list(map(lambda x: x.split(), sys.stdin.readlines()[1:]))

members = set()
target = 'ChongChong'
members.add(target)

for es in IN:
    isTargetAppeared = False
    for e in es:
        isTargetAppeared = isTargetAppeared or e in members

    if isTargetAppeared:
        for e in es:
            members.add(e)

print(len(members))
