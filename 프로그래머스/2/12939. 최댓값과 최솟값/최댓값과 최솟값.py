def solution(s):
    
    nums = s.split()
    
    min_num = max_num = int(nums[0])
    
    for num in nums[1:]:
        num = int(num)
        min_num = min(min_num, num)
        max_num = max(max_num, num)
    return " ".join(map(str, [min_num, max_num]))