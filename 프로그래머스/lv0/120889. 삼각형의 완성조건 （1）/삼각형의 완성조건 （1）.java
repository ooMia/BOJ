import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);
        boolean isValidTriangle = sides[0] + sides[1] > sides[2] ;
        return isValidTriangle ? 1 : 2;
    }
}