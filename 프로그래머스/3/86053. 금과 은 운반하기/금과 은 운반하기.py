def calc_case_per_arrival(time, g, s, w, t) -> list:
    """
    :param time: 경과 시간
    :param g: 각 도시의 금의 양
    :param s: 각 도시의 은의 양
    :param w: 각 도시의 트럭이 최대로 운반할 수 있는 광물의 양
    :param t: 각 도시의 건설 장소까지의 편도 시간
    :return: 경과된 시점에 각 도시에서부터 운반할 수 있는 금과 은의 최대량
    """

    res = []
    for i in range(len(g)):
        n_visit = max(0, (time - t[i]) // (2 * t[i]) + 1)
        max_gold = min(n_visit * w[i], g[i])
        max_silver = min(n_visit * w[i], s[i])
        max_weight = min(n_visit * w[i], max_gold + max_silver)
        res.append([max_gold, max_silver, max_weight])
    return res


def validation(a, b, g, s, w, t, time) -> bool:
    case = calc_case_per_arrival(time, g, s, w, t)
    if sum([c[2] for c in case]) >= (a + b):
        gold = sum([c[0] for c in case])
        silver = sum([c[1] for c in case])
        if gold >= a and silver >= b:
            return True
    return False


def solution(a: int, b: int, g: list, s: list, w: list, t: list) -> int:
    """
    어느 왕국에 하나 이상의 도시들이 있습니다. 왕국의 왕은 새 도시를 짓기로 결정하였습니다.
    해당 도시를 짓기 위해서는 도시를 짓는 장소에 금 a kg과 은 b kg이 전달되어야 합니다.
    각 도시에는 번호가 매겨져 있는데, i번 도시에는 금 g[i] kg, 은 s[i] kg, 그리고 트럭 한 대가 있습니다.

    i번 도시의 트럭은 오직 새 도시를 짓는 건설 장소와 i번 도시만을 왕복할 수 있으며,
    편도로 이동하는 데 t[i] 시간이 걸리고, 최대 w[i] kg 광물을 운반할 수 있습니다.
    (광물은 금과 은입니다. 즉, 금과 은을 동시에 운반할 수 있습니다.)
    모든 트럭은 같은 도로를 여러 번 왕복할 수 있으며 연료는 무한대라고 가정합니다.

    주어진 정보를 바탕으로 각 도시의 트럭을 최적으로 운행했을 때,
    새로운 도시를 건설하기 위해 금 a kg과 은 b kg을 전달할 수 있는 가장 빠른 시간을 반환합니다.

    :param a: 새 도시를 짓기 위해 필요한 금의 양
    :param b: 새 도시를 짓기 위해 필요한 은의 양
    :param g: 각 도시의 금의 양
    :param s: 각 도시의 은의 양
    :param w: 각 도시의 트럭이 최대로 운반할 수 있는 광물의 양
    :param t: 각 도시의 건설 장소까지의 편도 시간
    :return: 새 도시를 짓기 위해 금 a kg과 은 b kg을 전달할 수 있는 가장 빠른 시간
    """

    time_ceil = 1
    while validation(a, b, g, s, w, t, time_ceil) is False:
        time_ceil *= 2

    time_floor = time_ceil // 2
    while time_ceil - time_floor > 1:
        time = (time_ceil + time_floor) // 2
        if validation(a, b, g, s, w, t, time):
            time_ceil = time
        else:
            time_floor = time

    return time_ceil