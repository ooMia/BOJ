def char_to_int(c):
    n = ord(c)
    if ord('A') <= n <= ord('Z'):
        return 10 + n - ord('A')
    return n - ord('0')


chs, radix = input().split()
ls = list(map(char_to_int, [*chs]))

res = 0
for i in range(len(ls)):
    res += pow(int(radix), len(ls) - 1 - i) * ls[i]
print(res)
