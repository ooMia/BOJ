class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        boolean res = ">".equals(ineq) ? n > m : n < m;
        if ("=".equals(eq)) res |= n == m;
        return res ? 1: 0;
    }
}