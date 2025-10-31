from itertools import combinations 

def solution(nums):
    isPrime = [True for _ in range(3001)]
    for i in range(2, 3001):
        if isPrime[i] == True:
            j = i*2
            while j < 3001:
                isPrime[j] = False
                j += i
            
    count = 0
    a = combinations(nums, 3)
    for i in a:
        if isPrime[sum(i)] == True:
            count += 1
    return count