class Solution {
    public int solution(int a, int b) {
        String aS = String.valueOf(a), bS = String.valueOf(b);
        int n1 = Integer.valueOf(aS + bS);
        int n2 = 2 * a * b;
        return Math.max(n1, n2);
    }
}