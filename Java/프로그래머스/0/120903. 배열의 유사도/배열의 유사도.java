import java.util.*;

class Solution {
    public int solution(String[] s1, String[] s2) {
        Set<String> set1 = new HashSet<>(s1.length);
        for (var s: s1) set1.add(s);
        
        int count = 0;
        for (var s: s2) if (set1.contains(s)) ++count;
        return count;
    }
}