import java.util.TreeMap;

class Solution {
    public int[] solution(int[][] score) {
        var map = new TreeMap<Integer, Integer>();
        for (var s: score) {
            var k = s[0] + s[1];
            map.put(k, map.getOrDefault(k,0) + 1);
        }
        
        int rank = 1;
        for (var e: map.descendingMap().entrySet()) {
            var k = e.getKey();
            var v = e.getValue();
            map.put(k, rank);
            rank += v;
        }
        
        int[] answer = new int[score.length];
        for (int i=0; i<score.length; ++i)
            answer[i] = map.get(score[i][0] + score[i][1]);
        return answer;
    }
}