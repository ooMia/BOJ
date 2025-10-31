class Solution {
    public int solution(int n) {
        int answer = 1;
        for (int i=2; n>0 && i<=10; ++i){
            n /= i;
            if (n>0) ++answer;
        }
        return answer;
    }
}