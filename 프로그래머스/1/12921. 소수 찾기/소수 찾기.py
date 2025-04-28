def solution(n):
    isPrime = [0, 0] + [1 for _ in range(2, n+1)]
    for i in range(2, n+1):
        j = i+i
        if isPrime[i] == 1:
            while j < n+1:
                isPrime[j] = 0
                j += i
    return sum(isPrime)