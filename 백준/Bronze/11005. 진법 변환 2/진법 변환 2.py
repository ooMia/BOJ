import sys

n, radix = map(int, sys.stdin.readline().split())

res = []
pos = -1
while n > 0:
    pos += 1
    digit_cur = pow(radix, pos)
    digit_next = digit_cur * radix

    remainder = n % digit_next
    res.insert(0, remainder // digit_cur)

    n -= remainder

a = list(range(ord('0'), ord('9') + 1))
b = list(range(ord('A'), ord('Z') + 1))
ch = list(map(chr, a + b))

res = list(map(lambda n: ch[n], res))
print(*res, sep='')