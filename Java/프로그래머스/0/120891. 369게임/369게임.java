class Solution {
    public int solution(int order) {
        int count = 0;
        while(order > 0){
            int next = order % 10;
            if (next > 0 && next % 3 == 0) ++count;
            order /= 10;
        }
        return count;
    }
}