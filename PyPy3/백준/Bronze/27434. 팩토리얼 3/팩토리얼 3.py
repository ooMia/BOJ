import sys
sys.set_int_max_str_digits(0)

n = int(input())

res = 1
for i in range(1, n+1):
    res *= i
print(res)