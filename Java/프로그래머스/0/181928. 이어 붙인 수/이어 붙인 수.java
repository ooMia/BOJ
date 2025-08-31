class Solution {
    public int solution(int[] num_list) {
        var odd = new StringBuilder();
        var even = new StringBuilder();
        
        for (var n: num_list)
            if (n % 2 == 0) even.append(n);
            else odd.append(n);
        
        return Integer.parseInt(odd.toString()) + Integer.parseInt(even.toString());
    }
}