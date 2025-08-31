import java.util.Arrays;

class Solution {
    public int[] solution(int[][] score) {
        var N = score.length;
        Integer[] ranks = new Integer[N];
        for (int i=0; i<N; ++i)
            ranks[i] = score[i][0] + score[i][1];
        
        Arrays.sort(ranks);
        
        int[] answer = new int[N];
        for (int i=0; i<N; ++i) {
            answer[i] = N - binarySearch(ranks, score[i][0] + score[i][1]);
        }
        return answer;
    }
    
    int binarySearch(Integer[] a, int key) {
        // 기존 Arrays.sort는 동일 값에 대해 최소 인덱스를 반환하므로 수정
        int low = 0;
        int high = a.length - 1;
        int result = -1; // 최대 인덱스 저장 변수

        while (low <= high) {
            int mid = (low + high) >>> 1;
            long midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                result = mid;
                low = mid + 1;
            }
        }
        return result != -1 ? result : -(low + 1);
    }
}