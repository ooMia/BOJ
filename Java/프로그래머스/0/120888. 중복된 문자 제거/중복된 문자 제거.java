import java.util.LinkedHashSet;
import java.util.Set;

class Solution {
    public String solution(String my_string) {
        Set<Character> uniqueChars = new LinkedHashSet<>();
        for (char c : my_string.toCharArray()) {
            uniqueChars.add(c);
        }
        StringBuilder answer = new StringBuilder();
        for (char c : uniqueChars) {
            answer.append(c);
        }
        return answer.toString();
    }
}