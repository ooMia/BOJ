import java.util.Map;

public class Solution {

  public int solution(int angle) {
    // 각도에 따른 결과를 저장하는 Map 생성
    Map<String, Integer> dict = Map.of(
        "undefined", -1,
        "예각", 1,
        "직각", 2,
        "둔각", 3,
        "평각", 4);

    int res = dict.get("undefined");
    if (angle < 90)
      res = dict.get("예각");
    else if (angle == 90)
      res = dict.get("직각");
    else if (angle == 180)
      res = dict.get("평각");
    else if (angle > 90)
      res = dict.get("둔각");
    return res;
  }

}