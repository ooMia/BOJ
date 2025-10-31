class Solution {
    public int solution(int M, int N) {
        if (M-1 == 0) return N-1;
        return M-1 + M*(N-1);
    }
}