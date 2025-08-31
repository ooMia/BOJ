import java.util.*;

class Solution {
    public String solution(String s) {
        Map<Character, Integer> res = new TreeMap<>();
        var cs = s.toCharArray();
        for (var c : cs) {
            var prev = res.getOrDefault(c, 0);
            res.put(c, prev + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        for (var e : res.entrySet()) {
            if (e.getValue() == 1) sb.append(e.getKey());
        }
        return sb.toString();
    }
}