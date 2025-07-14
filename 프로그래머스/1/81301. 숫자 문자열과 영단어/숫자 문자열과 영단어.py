def solution(s):
    relation = {
        "zero": '0',
        "one": '1',
        "two": '2',
        "three": '3',
        "four": '4',
        "five": '5',
        "six": '6',
        "seven": '7',
        "eight": '8',
        "nine": '9'
    }
    
    answer = []
    sub_str = []
    for ch in s:
        if ch.isdigit():
            answer.append(ch)
        else:
            sub_str.append(ch)
            key = ''.join(sub_str)
            if len(sub_str) >= 3 and key in relation.keys():
                answer.append(relation[key])
                sub_str = []
    return int(''.join(answer))