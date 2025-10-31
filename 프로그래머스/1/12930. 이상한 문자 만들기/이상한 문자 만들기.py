def change(s):
    if len(s) < 1:
        return ' '
    result = []
    for i in range(len(s)):
        if i % 2 == 0:
            result.append(s[i].upper())
        else:
            result.append(s[i].lower())
    return ''.join(result)

def solution(s):
    result = []
    idx = 0
    for c in s:
        if c == ' ':
            result.append(' ')
            idx = 0
        elif idx % 2 == 0:
            result.append(c.upper())
            idx += 1
        else:
            result.append(c.lower())
            idx += 1
    return ''.join(result)