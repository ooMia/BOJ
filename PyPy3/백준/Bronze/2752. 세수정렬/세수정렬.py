numbers = map(int, input().split())
res = str.join(" ", map(str, sorted(numbers)))
print(res)