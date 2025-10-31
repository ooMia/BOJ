class Solution {
    public int solution(int n) {
        int decimal = 1, x = 1;
        while (decimal < n) {
            while (++x%3==0 || String.valueOf(x).contains("3"));
            ++decimal;
        }
        return x;
    }
}
