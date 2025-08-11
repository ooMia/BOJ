class Solution {
    public int solution(String my_string) {
        var ss = my_string.split(" ");
        int res = Integer.parseInt(ss[0]);
        
        for (int i=1; i<ss.length; ){
            var operator = ss[i++];
            var operand = Integer.parseInt(ss[i++]);
            if ("+".equals(operator)) res += operand;
            else if ("-".equals(operator)) res -= operand;
        }
        return res;
    }
}