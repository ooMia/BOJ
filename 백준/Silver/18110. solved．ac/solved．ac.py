from decimal import Decimal, ROUND_HALF_UP


def round_half_up(value: float) -> int:
    return int(Decimal(str(value)).to_integral_value(rounding=ROUND_HALF_UP))


def main():
    from sys import stdin

    n_lines = int(stdin.readline().strip())
    if n_lines < 1:
        print(0)
        return
    numbers = [int(line.strip()) for line in stdin.readlines()]
    sorted_numbers = sorted(numbers)

    exclude_percent = 0.15
    exclude_count = round_half_up(n_lines * exclude_percent)

    truncated_numbers = sorted_numbers[exclude_count : len(numbers) - exclude_count]

    average = sum(truncated_numbers) / len(truncated_numbers)
    print(round_half_up(average))
    
main()