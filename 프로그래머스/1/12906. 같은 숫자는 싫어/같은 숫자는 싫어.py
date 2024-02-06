def solution(arr):
    answer = [arr.pop(0)]
    for c in arr:
        if answer[-1] != c:
            answer.append(c)
    return answer
                
