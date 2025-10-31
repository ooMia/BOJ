def solution(s):
    answer = []
    record = dict()
    for i in range(len(s)):
        ch = s[i]
        if ch not in record.keys():
            answer.append(-1)
        else:
            answer.append(i - record[ch])
        record[ch] = i
    return answer