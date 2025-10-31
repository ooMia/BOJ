import java.util.Map;

class Solution {
    public String solution(String rsp) {
        Map<Character, Character> morse = Map.ofEntries(
                Map.entry('2', '0'),
                Map.entry('0', '5'),
                Map.entry('5', '2'));

        StringBuilder answer = new StringBuilder();
        for (char c : rsp.toCharArray()) {
            Character decoded = morse.get(c);
            if (decoded != null) {
                answer.append(decoded);
            }
        }
        return answer.toString();
    }
}