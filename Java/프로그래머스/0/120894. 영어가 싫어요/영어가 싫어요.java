import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public long solution(String numbers) {
        Map<String, String> mapper = new HashMap<>();
        mapper.put("zero", "0");
        mapper.put("one", "1");
        mapper.put("two", "2");
        mapper.put("three", "3");
        mapper.put("four", "4");
        mapper.put("five", "5");
        mapper.put("six", "6");
        mapper.put("seven", "7");
        mapper.put("eight", "8");
        mapper.put("nine", "9");

        StringBuilder sb = new StringBuilder();
        List<Character> temp = new ArrayList<>();
        for (char c : numbers.toCharArray()) {
            temp.add(c);
            var key = temp.stream().map(String::valueOf).reduce("", String::concat);
            if (mapper.containsKey(key)) {
                sb.append(mapper.get(key));
                temp.clear();
            }
            System.err.println(temp.toString());
            System.err.println(key);
        }
        return Long.parseLong(sb.toString());
    }
}