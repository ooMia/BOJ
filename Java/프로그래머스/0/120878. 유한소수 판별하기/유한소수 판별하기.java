import java.math.BigInteger;

class Solution {
    public int solution(int a, int b) {
        var gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
        b /= gcd;
        
        while ((b&1)==0) b>>=1;
        while (b%5==0) b/=5;

        return b==1 ? 1 : 2;
    }
}