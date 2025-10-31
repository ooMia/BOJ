class Solution {
    public int solution(String my_string) {
        int sum=0;
        StringBuilder buf = new StringBuilder();
        for (var c: my_string.toCharArray()) {
            if (Character.isDigit(c)) buf.append(c);
            else if (buf.length() > 0) {
                sum += Integer.parseInt(buf.toString());
                buf.setLength(0);
            }
        }
        if (buf.length() > 0) {
            sum += Integer.parseInt(buf.toString());
        }
        return sum;
    }
}