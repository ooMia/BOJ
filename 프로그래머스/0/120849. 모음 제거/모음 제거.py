# 문자열 my_string에서 모음을 제거한 문자열
def solution(my_string: str) -> str:
	vowel = ['a', 'e', 'i', 'o', 'u']
	return ''.join([i for i in my_string if i not in vowel])