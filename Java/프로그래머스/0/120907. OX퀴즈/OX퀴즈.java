import java.util.*;

class Solution {
    List<String> tmp = new ArrayList<>();
    
    public String[] solution(String[] quiz) {
        for (var q: quiz) calc(q);
        return tmp.toArray(new String[0]);
    }
    
    void calc(String s) {
        var ss = s.split(" ");
        int a = Integer.parseInt(ss[0]);
        int b = Integer.parseInt(ss[ss.length - 1]);
        for (int i=1; i<ss.length - 2;) {
            var operator = ss[i++];
            var operand = Integer.parseInt(ss[i++]);
            if ("+".equals(operator)) a += operand;
            else a -= operand;
        }
        compare(a, b);
    }
    
    void compare(int a, int b) {
        if (a == b) tmp.add("O");
        else tmp.add("X");
    }
}