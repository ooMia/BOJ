class Solution {
    public int solution(int chicken) {
        int coupon = chicken, answer = 0;
        while (coupon >= 10) {
            chicken = coupon / 10;
            coupon %= 10;
            
            answer += chicken;
            coupon += chicken;
        }
        return answer;
    }
}