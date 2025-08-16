class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        while (i <= j) {
            for (var c: String.valueOf(i).toCharArray())
                if (c == '0' + k) ++answer;
            ++i;
        }
        return answer;
    }
}