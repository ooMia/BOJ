import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int[][] dots) {
        // 가능한 조합을 배열로 정의
        int[][] cases = {
            {0, 1, 2, 3},
            {0, 2, 1, 3},
            {0, 3, 1, 2}
        };

        for (int[] c : cases) {
            var g1 = new Gradient(dots[c[0]], dots[c[1]]);
            var g2 = new Gradient(dots[c[2]], dots[c[3]]);
            if (g1.equals(g2)) {
                return 1;
            }
        }

        return 0;
    }
    
    class Gradient {
        final int sign, dx, dy;
        
        Gradient(int[] p1, int[] p2){
            var dx = p2[0] - p1[0];
            var dy = p2[1] - p1[1];
            this.sign = (dx * dy > 0) ? +1 : -1;

            dx = Math.abs(dx);
            dy = Math.abs(dy);
            var gcd = BigInteger.valueOf(dx).gcd(BigInteger.valueOf(dy)).intValue();
            this.dx = dx/gcd;
            this.dy = dy/gcd;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Gradient)) return false;
            Gradient g = (Gradient) o;
            return sign == g.sign && dx == g.dx && dy == g.dy;
        }
    }
}