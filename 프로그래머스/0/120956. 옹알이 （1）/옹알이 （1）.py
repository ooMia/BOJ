def solution(babbling: list) -> int:
    """
    :param babbling:
    :type babbling: list
    :return: 유효한 문자열의 개수
    :rtype: int
    """

    word_set = {"aya", "ye", "woo", "ma"}
    trie = make_trie(word_set)

    answer = 0
    for sentence in babbling:
        answer += 1 if is_valid(trie, sentence) else 0
    return answer


def make_trie(word_set: set) -> dict:
    """
    :param word_set: 단어 집합
    :return: 검색 트리
    """
    trie = {}
    for word in word_set:
        node = trie
        for i, ch in enumerate(word):
            if i == len(word) - 1:
                node[ch] = True
                break
            node = node.setdefault(ch, {})
    return trie


def is_valid(trie: dict, s: str) -> bool:
    """
    :param trie: 검색 트리
    :param s: 알파벳 소문자 문자열
    :return: s가 trie 내 문자열로만 이루어져 있는지 여부
    """
    node = trie
    for ch in s:
        if ch not in node:
            return False
        node = node[ch]
        if node is True:
            node = trie
    return node is trie