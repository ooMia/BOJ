class Solution {
    public int[][] solution(int[] num_list, int n) {
        int nRows = num_list.length / n, nCols = n;
        int[][] answer = new int[nRows][nCols];
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                answer[r][c] = num_list[r*nCols + c];
            }
        }
        return answer;
    }
}