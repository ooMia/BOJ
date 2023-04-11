import java.util.*;

class Solution {
    public int solution(int angle) {
        
        Map<String, Integer> dict = Map.of(
            "undefined", -1,
            "예각", 1,
            "직각", 2,
            "둔각", 3,
            "평각", 4
        );

        if (angle  < 90 ) return dict.get("예각");
        else if (angle == 90 ) return dict.get("직각");
        else if (angle == 180) return dict.get("평각");
        else if (angle  > 90 ) return dict.get("둔각");
        else return dict.get("undefined");
    }
}