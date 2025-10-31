import java.util.Arrays;

class Solution {
    public int solution(String before, String after) {
        var s1 = before.toCharArray();
        var s2 = after.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        return new String(s1).equals(new String(s2)) ? 1 : 0;
    }
}