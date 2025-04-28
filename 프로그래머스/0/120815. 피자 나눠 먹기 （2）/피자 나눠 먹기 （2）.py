def solution(n: int) -> int:
    """
    피자를 주문하면 여섯 조각으로 잘라 줍니다.
    n명이 주문한 피자를 남기지 않고 모두 먹기 위해 주문해야 하는 피자의 최소 판 수를 구합니다.

    :param n: 피자를 나눠먹을 사람의 수
    :return: 주문해야 하는 피자의 최소 판 수
    """
    return lcm(n, 6) // 6


def gcd(a: int, b: int) -> int:
    if b == 0:
        return a
    return gcd(b, a % b)


def lcm(a: int, b: int) -> int:
    return a * b // gcd(a, b)