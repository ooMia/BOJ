def solution(k, m, score):
    if len(score) < m:
        return 0
    answer = 0
    score = sorted(score, reverse=True)
    for i in range(len(score) // m):
        arr = score[i*m : i*m+m]
        answer += arr[-1] * m
    return answer