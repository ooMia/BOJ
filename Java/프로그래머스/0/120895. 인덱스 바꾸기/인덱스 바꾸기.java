import java.util.*;

class Solution {
    public String solution(String my_string, int num1, int num2) {
        var cs = my_string.toCharArray();
        var tmp = my_string.charAt(num1);
        cs[num1] = cs[num2];
        cs[num2] = tmp;
        return new String(cs);
    }
}