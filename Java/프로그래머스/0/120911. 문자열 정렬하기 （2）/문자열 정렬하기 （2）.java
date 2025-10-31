import java.util.Arrays;

class Solution {
    public String solution(String my_string) {
        var cs = my_string.toLowerCase().toCharArray();
        Arrays.sort(cs);
        return new String(cs);
    }
}