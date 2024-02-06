def solution(price, money, count):
    ans = (price * count * (count + 1) // 2) - money
    return max(0, ans)