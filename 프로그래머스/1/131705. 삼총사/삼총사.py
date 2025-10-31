import itertools

def solution(numbers):
    answer = 0
    for comb in list(itertools.combinations(numbers, 3)):
        answer += 1 if sum(comb) == 0 else 0
    return answer