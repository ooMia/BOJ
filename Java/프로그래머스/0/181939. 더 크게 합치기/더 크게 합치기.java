class Solution {
    public int solution(int a, int b) {
        var sA = String.valueOf(a);
        var sB = String.valueOf(b);
        
        var nA = Integer.parseInt(sA + String.valueOf(b));
        var nB = Integer.parseInt(sB + String.valueOf(a));
        
        return Math.max(nA, nB);
    }
}