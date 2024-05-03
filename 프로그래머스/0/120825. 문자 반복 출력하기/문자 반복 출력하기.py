# my_string에 들어있는 각 문자를 n만큼 반복한 문자열
def solution(my_string, n):
    answer = ''
    for s in my_string:
        answer += s * n
    return answer