def solution(k, scores):
    answer = [scores[0]]
    hof = [scores[0]]
    for score in scores[1:]:
        for (i, n) in enumerate(hof):
            if score >= hof[i]:
                hof.insert(i, score)
                break
            if i == len(hof) - 1:
                hof.append(score)
                break
        if len(hof) > k:
            hof.pop()
        answer.append(hof[-1])
    return answer