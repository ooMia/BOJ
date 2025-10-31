class Solution {
    public int solution(int num, int k) {
        String s = String.valueOf(num);
        for (int i=0; i<s.length(); ++i) {
            if (s.charAt(i) == k + '0') return i+1;
        }
        return -1;
    }
}