IN = input()
substring = set()

for l in range(1, len(IN) + 1):
    for i in range(len(IN) + 1 - l):
        substring.add(IN[i:i+l])
print(len(substring))
