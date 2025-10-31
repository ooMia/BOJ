class Solution {
    public int solution(int[] array) {
        int count = 0;
        for (int n: array){
            while (n>0) {
                int k=n%10;
                if (k==7) ++count;
                n/=10;
            }
        }
        return count;
    }
}