public class Solution {

  private int sumFrom1ToN(int n) {
    return n * (n + 1) / 2;
  }

  public int solution(int n) {
    int floorEvenN = n - n % 2;
    int answer = 2 * sumFrom1ToN(floorEvenN / 2);
    return answer;
  }
}
