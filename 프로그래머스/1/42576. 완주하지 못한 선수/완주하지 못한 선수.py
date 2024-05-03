def solution(participant: list, completion: list) -> str:
    """
    완주하지 못한 선수의 이름을 반환합니다.

    - 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주했다고 가정합니다.
    - 참가자 중에는 동명이인이 있을 수 있습니다.

    :param participant: 마라톤에 참여한 선수들의 이름
    :type participant: list
    :param completion: 마라톤을 완주한 선수들의 이름
    :type completion: list
    :return: 완주하지 못한 선수의 이름
    :rtype: str
    """

    from collections import defaultdict
    name_table = defaultdict(int)
    for name in participant:
        name_table[name] += 1
    for name in completion:
        name_table[name] -= 1

    for name, count in name_table.items():
        if count > 0:
            return name
