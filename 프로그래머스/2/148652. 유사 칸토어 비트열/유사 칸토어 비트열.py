def solution(n, l, r):
    if n == 1:
        return sum([1, 1, 0, 1, 1][l - 1: r])

    block_size = 5 ** (n - 1)
    block_n_start = (l - 1) // block_size + 1
    block_n_end = (r - 1) // block_size + 1
    res = 0

    for block_n in range(block_n_start, block_n_end + 1):
        if block_n != 3:
            if block_n_start == block_n_end:
                res += solution(n - 1, (l - 1) % block_size + 1, (r - 1) % block_size + 1)
            elif block_n == block_n_start:
                res += solution(n - 1, (l - 1) % block_size + 1, block_size)
            elif block_n == block_n_end:
                res += solution(n - 1, 1, (r - 1) % block_size + 1)
            else:
                res += 4 ** (n - 1)
    return res

