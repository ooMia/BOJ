def solution(babbling: list) -> int:
    """
    :param babbling:
    :type babbling: list
    :return: 유효한 문자열의 개수
    :rtype: int
    """

    word_set = {"aya", "ye", "woo", "ma"}
    pool = set()
    import itertools
    for k in range(1, len(word_set) + 1):
        for word in itertools.permutations(word_set, k):
            pool.add("".join(word))

    answer = 0
    for babble in babbling:
        if babble in pool:
            answer += 1
    return answer