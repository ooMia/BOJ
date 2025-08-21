class Solution {
    public String solution(String _code) {
        int mode = 0;
        var sb = new StringBuilder();
        var code = _code.toCharArray();
        for (int idx=0; idx<code.length; ++idx) {
            var c = code[idx];
            if (c == '1') mode ^= 1;
            else if (idx % 2 == mode) sb.append(c); 
        }
        return sb.length() > 0 ? sb.toString() : "EMPTY";
    }
}