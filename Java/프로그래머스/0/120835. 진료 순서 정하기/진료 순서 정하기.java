import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(int[] emergency) {
        Map<Integer, Integer> map = new java.util.HashMap<>();

        int[] sorted = emergency.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < sorted.length; i++) {
            map.put(sorted[i], sorted.length - i);
        }
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < emergency.length; i++) {
            answerList.add(map.get(emergency[i]));
        }
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}