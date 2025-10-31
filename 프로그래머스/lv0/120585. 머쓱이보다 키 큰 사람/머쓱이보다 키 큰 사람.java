class Solution {
    public int solution(int[] array, int height) {
        int answer = 0;
        for (int e : array) answer += (e > height ? 1 : 0);
        return answer;
    }
}