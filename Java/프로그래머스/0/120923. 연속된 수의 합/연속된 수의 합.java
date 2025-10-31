import java.util.stream.IntStream;

class Solution {
    public int[] solution(int num, int total) {
        if (num % 2 == 1) {
            int mid = total / num, start = mid - (num-1) / 2;
            return IntStream.range(start, start+num).toArray();
        }
        
        int mid = total / num, start = mid - (num-1)/2;
        return IntStream.range(start, start+num).toArray();
    }
}