def solution(answers):
    
    patterns = [
        [1, 2, 3, 4, 5],
        [2, 1, 2, 3, 2, 4, 2, 5],
        [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    ]

    results = [0, 0, 0]
    
    for (i, answer) in enumerate(answers):
        results[0] += 1 if patterns[0][i % 5 ] == answer else 0
        results[1] += 1 if patterns[1][i % 8 ] == answer else 0
        results[2] += 1 if patterns[2][i % 10] == answer else 0
        
    answer = []
    for (i, result) in enumerate(results):
        if result == max(results):
            answer.append(i+1)
    return sorted(answer)