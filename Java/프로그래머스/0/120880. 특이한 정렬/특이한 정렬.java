import java.util.Arrays;

class Solution {
    public int[] solution(int[] numlist, int n) {
        var numlistBoxed = Arrays.stream(numlist).boxed().toArray(Integer[]::new);

        Arrays.sort(numlistBoxed, (n1, n2) -> {
            var d1 = Math.abs(n - n1);
            var d2 = Math.abs(n - n2);
            if (d1 == d2) return Integer.compare(n2, n1);
            else return Integer.compare(d1, d2);
        });

        return Arrays.stream(numlistBoxed).mapToInt(Integer::intValue).toArray();
    }
}