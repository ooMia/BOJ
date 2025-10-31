class Solution {
    public int[] solution(int[] array) {
        int iRes=0, res=array[0];
        for (int i=1; i<array.length; ++i) {
            if (res < array[i]) {
                iRes = i;
                res = array[i];
            }
        }
        return new int[]{res, iRes};
    }
}