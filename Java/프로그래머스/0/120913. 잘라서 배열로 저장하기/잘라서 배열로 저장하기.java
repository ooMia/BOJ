class Solution {
    public String[] solution(String my_str, int n) {
        var len = my_str.length();
        var size = len/n + (len % n == 0 ? 0 : 1);
        String[] res= new String[size];
        
        int iStart=0, iEnd=n;
        for (int i=0; i<size; ++i) {
            res[i] = my_str.substring(iStart, iEnd);
            iStart=iEnd;
            iEnd=Math.min(iEnd+n, len);
        }
        return res;
    }
}
