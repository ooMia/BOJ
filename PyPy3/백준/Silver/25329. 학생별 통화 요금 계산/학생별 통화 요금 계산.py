from collections import defaultdict

def calc_fee(total_time):
    base_time = 100
    base_fee = 10
    unit_time = 50
    unit_fee = 3
    if total_time <= base_time:
        return base_fee
    extra = total_time - base_time
    units = (extra + unit_time - 1) // unit_time  # 올림 처리
    return base_fee + units * unit_fee

n = int(input())
usage = defaultdict(int)
for _ in range(n):
    time_str, name = input().split()
    h, m = map(int, time_str.split(":"))
    minutes = h * 60 + m
    usage[name] += minutes

# 요금이 많은 순, 같으면 이름 오름차순
result = []
for name, total_time in usage.items():
    fee = calc_fee(total_time)
    result.append((fee, name))
result.sort(key=lambda x: (-x[0], x[1]))
for fee, name in result:
    print(f"{name} {fee}")
