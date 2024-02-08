

def solution(a, b, n):
    count = 0
    while n >= a:
        add = (n // a) * b
        count += add
        n %= a
        n += add
    return count

