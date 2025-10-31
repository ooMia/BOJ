def solution(n, arr1, arr2):
    answer = []
    for (bn1, bn2) in zip(arr1, arr2):
        num = list(bin(bn1 | bn2))[2 : ]
        num = list('0' * (n - len(num))) + num
        result = ''.join(list(map(lambda n: '#' if n=='1' else ' ', num)))
        print(result)
        answer.append(result)
    return answer