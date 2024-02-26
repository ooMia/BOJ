def solution(dartResult) -> int:
    number_set = set("1234567890")
    multiply_set = list(" SDT")
    option_set = set("*#")
    
    score_history = list()
    score = ''
    
    for ch in dartResult:
        if ch in option_set:
            score_history = apply_option(score_history, ch)
        elif ch in multiply_set:
            score = int(score)
            score **= multiply_set.index(ch)
            score_history.append(score)
            score = ''
        elif ch in number_set:
            score += ch
    return sum(score_history)

def apply_option(_score_history: list, _option: str) -> list:
    scores = _score_history.copy()
    if _option == "*":
        scores[-1] *= 2
        if 2 <= len(scores):
            scores[-2] *= 2
    elif _option == "#":
        scores[-1] *= -1
    return scores
