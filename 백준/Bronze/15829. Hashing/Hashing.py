def hash(s: str) -> int:
    r = 31
    M = 1234567891

    def char_to_int(ch):
        return ord(ch) - ord("a") + 1

    res = 0
    for i, ch in enumerate(s):
        res += (char_to_int(ch) * (r**i)) % M
    return res % M


from sys import stdin

lines = stdin.readlines()
print(hash(lines[1].strip()))