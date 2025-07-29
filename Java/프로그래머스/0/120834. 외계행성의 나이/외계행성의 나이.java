class Solution {
    public String solution(int age) {
        StringBuilder sb = new StringBuilder();
        String ageStr = String.valueOf(age);
        for (char c : ageStr.toCharArray()) {
            // Convert each digit to its corresponding character
            sb.append((char) ('a' + (c - '0')));
        }
        return sb.toString();
    }
}