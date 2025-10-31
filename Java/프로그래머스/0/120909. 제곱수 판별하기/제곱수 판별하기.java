class Solution {
    public int solution(int n) {
        var q = (int) Math.sqrt((double) n);
        return q * q == n ? 1 : 2;
    }
}