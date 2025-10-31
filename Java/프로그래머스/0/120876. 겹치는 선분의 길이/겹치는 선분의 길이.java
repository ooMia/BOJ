import java.util.Arrays;

class Solution {
    public int solution(int[][] lines) {
        int[] wrapped = new int[200 + 1];
        for (var l : lines)
            for (int i = l[0]; i < l[1]; ++i)
                ++wrapped[i + 100];
        return Arrays.stream(wrapped).map(x -> (x > 1) ? 1 : 0).sum();
    }
}