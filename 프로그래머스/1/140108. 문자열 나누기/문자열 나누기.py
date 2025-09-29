def solution(s: str) -> int:
    """
    s를 분리하여 남은 부분이 없을 때까지 반복합니다.
    :param s: 영어 소문자 문자열
    :return: 문자열 '분해' 결과 개수
    """
    answer = 0
    while len(s) > 0:
        s = parse(s)
        answer += 1
    return answer


def parse(s: str) -> str:
    """
    첫 번째 문자의 출현 빈도와 그 외 다른 문자들의 출현 빈도가 같아질 때 문자열을 분리합니다.
    예를 들어, "aabcab"는 "aabc"와 "ab"로 분리됩니다.
    문자열에서 더 이상 읽을 글자가 없다면, 그 시점에서 분리합니다.
    :param s: 전체 문자열
    :return: 분해 후 남은 부분 문자열
    """
    if len(s) <= 2:
        return ""

    s = list(s)
    first_ch, other_ch = s.pop(0), -1
    ch_table = {first_ch: 1, other_ch: 0}

    while s:
        ch = s.pop(0)
        if ch != first_ch:
            ch = other_ch
        ch_table[ch] += 1
        if ch_table[first_ch] == ch_table[other_ch]:
            break

    return "".join(s)