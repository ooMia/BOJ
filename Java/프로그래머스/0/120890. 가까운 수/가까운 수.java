class Solution {
    public int solution(int[] array, int n) {
        int cur = array[0];
        for (int i=1; i<array.length; ++i) {
            var diff1 = Math.abs(n - cur);
            var diff2 = Math.abs(n - array[i]);
            
            if (diff1 == diff2) cur = Math.min(cur, array[i]);
            else if (diff2 < diff1) cur = array[i];
        }
        return cur;
    }
}