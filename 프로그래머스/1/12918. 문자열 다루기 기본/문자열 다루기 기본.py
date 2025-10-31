def solution(s):
    if not(len(s) == 4 or len(s) == 6):
        return False
    return s.isdigit()