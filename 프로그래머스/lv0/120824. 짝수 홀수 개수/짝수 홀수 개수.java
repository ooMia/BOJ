class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = {0, 0};
        for (int num : num_list) ++answer[num % 2];
        return answer;
    }
}