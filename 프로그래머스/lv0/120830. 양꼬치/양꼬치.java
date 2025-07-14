class Solution {

  // food: 양꼬치, drink: 음료수
  private int
      costPerFood = 12000,
      costPerDrink = 2000,
      serviceDrinkPerFood = 10;

  public int solution(int n, int k) {
    int serviceDrink = n / serviceDrinkPerFood;
    int answer = costPerFood * n + costPerDrink * (k - serviceDrink);
    return answer;
  }
}