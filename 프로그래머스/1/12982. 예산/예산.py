def solution(d, budget):
    count = 0
    for n in sorted(d):
        budget -= n
        count += 1
        if budget <= 0:
            break
    return count - (1 if budget < 0 else 0)
