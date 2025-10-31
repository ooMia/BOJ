def solution(n, m, section):
    min_val = section[0]
    max_val = min_val + m - 1
    count = 1
    
    for i in range(1, len(section)):
        if max_val < section[i]:
            min_val = section[i]
            max_val = min_val + m - 1
            count += 1
    
    return count