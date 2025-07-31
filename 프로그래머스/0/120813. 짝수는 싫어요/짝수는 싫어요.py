# n 이하의 홀수가 오름차순으로 담긴 배열
def solution(n):
    answer = []
    # [1, n+1]
    for i in range(1, n+1):
        if i % 2 == 1:
            answer.append(i)
    return answer