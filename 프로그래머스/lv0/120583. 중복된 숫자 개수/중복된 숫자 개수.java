public class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        for (int e : array) answer += (e == n ? 1 : 0);
        return answer;
    }
}
