class Solution {
    public int solution(int[] num_list) {
        int mul = 1, sumSq = 0;
        for (int i=0; i<num_list.length; ++i) {
            var n = num_list[i];
            mul *= n; sumSq += n;
        }
        sumSq *= sumSq;
        
        return mul < sumSq ? 1 : 0;
    }
}