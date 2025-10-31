class Solution {
    public String solution(String code) {
        int mode = 0;
        var sb = new StringBuilder();
        for (int idx=0; idx<code.length(); ++idx) {
            var c = code.charAt(idx);
            if (c == '1') mode ^= 1;
            else if (idx % 2 == mode) sb.append(c); 
        }
        return sb.length() > 0 ? sb.toString() : "EMPTY";
    }
}