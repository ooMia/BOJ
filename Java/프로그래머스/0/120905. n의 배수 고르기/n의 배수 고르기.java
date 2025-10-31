import java.util.*;

class Solution {
    public int[] solution(int n, int[] numlist) {
        List<Integer> tmp = new ArrayList<>();
        for (var k: numlist) if (k % n == 0) tmp.add(k);
        return tmp.stream().mapToInt(Integer::valueOf).toArray();
    }
}