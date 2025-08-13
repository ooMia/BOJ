import java.util.Arrays;
class Solution {
    public int solution(int[] numbers) {
        Arrays.sort(numbers);
        var N = numbers.length;
        return Math.max(numbers[0] * numbers[1], numbers[N-1] * numbers[N-2]);
    }
}