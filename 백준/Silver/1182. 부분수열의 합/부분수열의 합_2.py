import sys


def search(nList, partialSum, diffSum):

    if len(nList) < 1:
        return 1 if partialSum == target else 0

    return search(nList[1:], partialSum, diffSum) +\
        search(nList[1:], partialSum + abs(nList[0]), diffSum + abs(nList[0]))

    # function description: search(nList, partialSum, diffSum)
    # 모든 탐색은 순차적으로 진행한다.
    # (nList가 오름차순 정렬된 상태로 들어오므로, 최솟값에서 시작하여 최댓값 방향으로 진행됨)
    # nList[0]에 대한 선택 여부를 기준으로 브루트포스의 재귀적 구현 시도

    # 탐색의 각 단계는 각 요소의 선택 여부에 따라
    #   선택된 수열의 합 partialSum,
    #   선택이 결정되지 않은 나머지 수열의 합 diffSum
    # 에 대한 상태를 최신화한다.

    # 각 요소 e를 선택했을 때, 그 크기에 따라 적용 방식이 달라진다.
    #   if e >= 0: partialSum += e, diffSum -= e
    #   if e < 0: partialSum -= e, diffSum += e
    # > 일반화하면 partialSum += abs(e), diffSum += abs(e)

# --------------------------------------------------------------------------- #
IN = list(map(lambda x: list(map(int, x.split())), sys.stdin.readlines()))

# 주어진 목표값: target
N, target = IN[0]

# nList 오름차순 정렬
nList = sorted(IN[1])

# 순차 탐색하여 음수의 총합 negSum과 양수의 총합 posSum을 구한다.
negSum, posSum = 0, 0
for n in nList:
    if n < 0: negSum += n
    else: posSum += n

# 초기 탐색은 partialSum = negSum, diffSum = posSum에서 시작한다.
# 단, 음수들의 총합 negSum이 target과 같다면 결과값에서 1을 뺀다.
res = search(nList, negSum, posSum) - (1 if target == 0 else 0)
print(res)
