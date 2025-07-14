# 문자열 my_string에서 모음을 제거한 문자열
def solution(my_string: str) -> str:
	vowels = 'aeiou'
	for i in vowels:
		my_string = my_string.replace(i, '')
	return my_string