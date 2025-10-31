import java.util.*;

class Solution {
    public int[] solution(int n) {
        SortedSet<Integer> res = new TreeSet<>();
        
        for (int i=1; i*i<=n; ++i) {
            if (n % i == 0) {
                res.add(i);
                res.add(n/i);
            }
        }
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}