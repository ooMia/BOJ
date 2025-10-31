import sys
from typing import List

def read_input():
    """입력을 읽어서 팀 수와 능력치 행렬을 반환"""
    n = int(input())
    ability_matrix = []
    for _ in range(n):
        row = list(map(int, input().split()))
        ability_matrix.append(row)
    return n, ability_matrix

def calculate_team_power(team: List[int], ability_matrix: List[List[int]]) -> int:
    """팀의 총 능력치를 계산"""
    total_power = 0
    for i in team:
        for j in team:
            total_power += ability_matrix[i][j]
    return total_power

def calculate_power_difference(team_a: List[int], team_b: List[int], ability_matrix: List[List[int]]) -> int:
    """두 팀 간의 능력치 차이를 계산"""
    power_a = calculate_team_power(team_a, ability_matrix)
    power_b = calculate_team_power(team_b, ability_matrix)
    return abs(power_a - power_b)

def find_minimum_difference(n: int, ability_matrix: List[List[int]]) -> int:
    """백트래킹을 통해 최소 능력치 차이를 찾음"""
    min_difference = float('inf')
    team_size = n // 2
    
    def backtrack(current_index: int, current_team: List[int]):
        nonlocal min_difference
        
        # 팀 구성이 완료된 경우
        if len(current_team) == team_size:
            # 나머지 선수들로 상대팀 구성
            opponent_team = [i for i in range(n) if i not in current_team]
            
            # 두 팀 간의 능력치 차이 계산
            difference = calculate_power_difference(current_team, opponent_team, ability_matrix)
            min_difference = min(min_difference, difference)
            return
        
        # 현재 인덱스부터 끝까지 선수들을 하나씩 팀에 추가해보기
        for i in range(current_index, n):
            if i not in current_team:
                # 선수를 팀에 추가
                current_team.append(i)
                backtrack(i + 1, current_team)
                # 백트래킹: 선수를 팀에서 제거
                current_team.pop()
    
    # 백트래킹 시작 (빈 팀으로 시작)
    backtrack(0, [])
    return min_difference

# 표준 입력 설정
input = sys.stdin.readline

# 입력 읽기
n, ability_matrix = read_input()

# 최소 능력치 차이 찾기
result = find_minimum_difference(n, ability_matrix)

# 결과 출력
print(result)
