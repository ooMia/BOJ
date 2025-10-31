def solution(array: list) -> int:
    """
    :param array: 음이 아닌 정수의 배열
    :type array: list
    :return: 주어진 배열의 최빈값. 여러개일 경우 -1을 반환
    :rtype: int
    """

    from collections import defaultdict
    freq = defaultdict(int)
    for e in array:
        freq[e] += 1

    res = sorted(freq.items(), key=lambda x: x[1], reverse=True)[:2]
    if len(res) == 1 or res[0][1] != res[1][1]:
        return res[0][0]
    return -1