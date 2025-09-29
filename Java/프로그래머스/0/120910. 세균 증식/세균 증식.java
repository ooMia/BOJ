class Solution {
    public int solution(int n, int t) {
        int answer = n;
        answer *= (1 << t);
        return answer;
    }
}