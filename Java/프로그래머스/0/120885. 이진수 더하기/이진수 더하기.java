import java.math.BigInteger;

class Solution {
    public String solution(String bin1, String bin2) {
        BigInteger num1 = new BigInteger(bin1, 2);
        BigInteger num2 = new BigInteger(bin2, 2);
        BigInteger sum = num1.add(num2);

        return sum.toString(2);
    }
}