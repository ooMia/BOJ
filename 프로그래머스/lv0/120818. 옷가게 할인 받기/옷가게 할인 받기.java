import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
  private double getDiscountRate(int cost) {
    Map<Integer, Double> discountRate = Map.of(
        500000, 0.2,
        300000, 0.1,
        100000, 0.05,
        0, 0.0);
    for (int k : discountRate.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()))
      if (cost >= k)
        return discountRate.get(k);
    return -1; // ERROR: Parameter < 0
  }

  public int solution(int price) {
    double answer = price * (1.0 - getDiscountRate(price));
    return (int) answer;
  }
}