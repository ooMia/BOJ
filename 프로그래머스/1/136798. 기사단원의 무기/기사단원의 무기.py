import math

def get_number_of_dividers(n):
    total = 0
    limit = int(math.sqrt(n)) + 1
    
    for i in range(1, limit):
        if n % i == 0:
            total += 2 if i * i != n else 1
    return total

def solution(number, limit, power):
    numbers = range(1, number + 1)
    total = 0
    for number in numbers:
        n_div = get_number_of_dividers(number)
        total += power if limit < n_div else n_div
    return total