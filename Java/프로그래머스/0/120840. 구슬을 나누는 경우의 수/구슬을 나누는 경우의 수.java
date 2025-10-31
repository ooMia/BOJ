import java.math.BigInteger;

class Solution {
    public int solution(int balls, int share) {
        return combination(balls, share);
    }

    int combination(int n, int m) {
        BigInteger res = BigInteger.ONE;
        for (int i = 0; i < m; i++) {
            res = res.multiply(BigInteger.valueOf(n - i));
            res = res.divide(BigInteger.valueOf(i + 1));
        }
        return res.intValue();
    }
}