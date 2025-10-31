import java.util.ArrayList;

class Solution {
    public int solution(String A, String B) {
        var A0 = A.charAt(0);
        // String B에서 A0가 등장하는 위치의 인덱스에 대한 배열을 구한다.
        var indices = new ArrayList<Integer>();
        for (int i = 0; i < B.length(); ++i) {
            if (B.charAt(i) == A0) {
                indices.add(i);
            }
        }

        // 각 인덱스에 대해 회전 문자열을 생성한다.
        // subString(B, i) + subString(B, 0, i)
        for (int i : indices) {
            var rotated = B.substring(i) + B.substring(0, i);
            if (rotated.equals(A)) {
                return i;
            }
        }
        return -1;
    }
}