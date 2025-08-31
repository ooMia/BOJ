class Solution {
    public int solution(int[] numbers, int k) {
        int next = 0;
        
        for(int i=1; i<k; ++i){
            next = (next + 2) % numbers.length;
        }
        
        return numbers[next];
    }
}