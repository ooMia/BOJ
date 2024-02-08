import itertools

def solution(numbers):
    return sorted(set(map(sum, itertools.combinations(numbers, 2))))