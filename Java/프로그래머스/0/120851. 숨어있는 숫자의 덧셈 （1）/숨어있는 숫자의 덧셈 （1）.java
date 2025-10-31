import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String my_string) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : my_string.toCharArray()) {
            if (Character.isDigit(c)) {
                numbers.add(Character.getNumericValue(c));
            }
        }
        return numbers.stream().reduce(0, Integer::sum);
    }
}