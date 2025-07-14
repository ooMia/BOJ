def solution(s, n):
    gap = ord('z') - ord('a') + 1
    n %= gap
    
    answer = []
    for ch in s:
        if ch == ' ':
            answer.append(ch)
            continue
        elif ord('a') <= ord(ch) <= ord('z'):
            base = ord('a')
        elif ord('A') <= ord(ch) <= ord('Z'):
            base = ord('A')
        asc = (ord(ch) - base + n) % gap + base
        answer.append(chr(asc))
    return ''.join(answer)