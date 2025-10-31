class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < my_string.length()) {
            if (i == s) {
                for (int j = 0; j < overwrite_string.length(); ++j, ++i) {
                    sb.append(overwrite_string.charAt(j));
                }
            } else {
                sb.append(my_string.charAt(i++));
            }
        }

        return sb.toString();
    }
}