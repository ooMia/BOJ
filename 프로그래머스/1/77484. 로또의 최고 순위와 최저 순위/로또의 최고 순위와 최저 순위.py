# 구매한 로또 번호 lottos, 당첨 번호 win_nums
# 당첨 가능한 최고 순위와 최저 순위
def solution(lottos: list, win_nums: list):
    match: int = n_equals(lottos, win_nums)
    zero = n_zero(lottos)
    return get_range_ranks(match, zero)[::-1]


def n_equals(a: list, b: list):
    return len(set(a) & set(b))


def n_zero(a: list):
    return a.count(0)


def get_range_ranks(n_match: int, n_zero: int) -> list:
    pos = [n_match, n_match + n_zero]
    return list(map(lambda x: 6 if x < 2 else 7 - x, pos))