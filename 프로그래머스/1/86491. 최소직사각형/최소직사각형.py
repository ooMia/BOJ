def solution(sizes):
    max_max = 0
    max_min = 0
    for size in sizes:
        if max_max < max(size):
            max_max = max(size)
        if max_min < min(size):
            max_min = min(size)
    return max_max * max_min