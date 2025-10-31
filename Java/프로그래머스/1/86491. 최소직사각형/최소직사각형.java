import java.util.Arrays;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0, maxHeight = 0;
        for (int[] size : sizes)
        {
            Arrays.sort(size);
            maxWidth = Math.max(maxWidth, size[0]);
            maxHeight = Math.max(maxHeight, size[1]);
        }
        int answer = maxWidth * maxHeight;
        return answer;
    }
}