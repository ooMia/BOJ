import java.util.Arrays;

class Solution {
    final boolean[] isComposite;
    
    Solution(){
        int limit = 100;
        isComposite = new boolean[limit + 1];
        for (int n=2; n<=limit/2; ++n) {
            for (int k=n*2; k<=limit; k+=n) isComposite[k] = true;
        }
    }
    
    public int solution(int n) {
        int answer = 0;
        for (int i=4; i<=n; ++i) answer += isComposite[i] ? 1 : 0;
        return answer;
    }
}