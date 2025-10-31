# 최대로 마실 수 있는 아메리카노의 잔 수와 남는 돈
def solution(money):
    # 아이스 아메리카노는 한잔에 5,500원
    price = 5500
    amount = money // price
    change = money % price
    answer = [amount, change]
    return answer