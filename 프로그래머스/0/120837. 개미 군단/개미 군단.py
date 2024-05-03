# 공격력: 장군 5, 병정 3, 일개미 1
# 사냥감의 체력 hp에 대한 최소 개미의 수
def solution(hp: int):
    answer = 0
    atk = [5, 3, 1]
    for a in atk:
        answer += hp // a
        hp %= a
    return answer