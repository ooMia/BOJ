def solution(triangle):
    node_max = [None, triangle[0]]
    cur_lvl = 1
    for t in triangle[1:]:
        node_max.append([0 for _ in t])
        cur_lvl += 1
        for (i, node) in enumerate(t):
            prev_max = get_max_prev_level(node_max, cur_lvl, i)
            node_max[cur_lvl][i] = max([node_max[cur_lvl][i], prev_max + node])

    return max(node_max[cur_lvl])


def get_max_prev_level(_node_max: list, _level: int, _idx: int):
    [idx_from, idx_to] = max([_idx - 1, 0]), min([_idx, _level - 2])
    accessible = _node_max[_level - 1][idx_from: idx_to + 1]
    return max(accessible)
