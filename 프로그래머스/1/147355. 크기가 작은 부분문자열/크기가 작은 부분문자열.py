def solution(t, p):
    count = 0
    for i in range(len(t) - len(p) + 1):
        num = int(''.join(t[i: i + len(p)]))
        if num <= int(p):
            count += 1
    return count