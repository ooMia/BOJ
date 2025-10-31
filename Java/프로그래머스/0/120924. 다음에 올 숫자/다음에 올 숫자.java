class Solution {
    public int solution(int[] common) {
        int gap0 = common[1] - common[0];
        int gap1 = common[2] - common[1];

        int N = common.length;
        if (gap0 == gap1) return common[N-1] + gap0;
        int r = gap1 / gap0;
        return common[0] * (int) Math.pow(r, N);
    }
}