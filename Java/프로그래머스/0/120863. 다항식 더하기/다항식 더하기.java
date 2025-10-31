class Solution {
    public String solution(String polynomial) {
        var tokens = polynomial.split(" ");

        int[] coefs = new int[2];
        for (var token : tokens) {
            if ("+".equals(token))
                continue;

            var N = token.length();
            if ('x' == token.charAt(N - 1)) {
                if (N == 1) {
                    ++coefs[1];
                    continue;
                }
                var s = token.substring(0, N - 1);
                coefs[1] += Integer.parseInt(s);
            }
            else
                coefs[0] += Integer.parseInt(token);
        }

        StringBuilder sb = new StringBuilder();
        if (coefs[1] == 1)
            sb.append("x");
        else if (coefs[1] > 1)
            sb.append(coefs[1]).append("x");
        if (coefs[0] > 0 && coefs[1] > 0)
            sb.append(" + ");
        if (coefs[0] > 0)
            sb.append(coefs[0]);
        return sb.toString();
    }
}