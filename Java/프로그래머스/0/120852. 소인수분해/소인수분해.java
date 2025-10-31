import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int N) {
        // 소인수 리스트 반환
        List<Integer> factors = new ArrayList<>();

        int n = N;
        if (n % 2 == 0) {
            factors.add(2);
            while (n % 2 == 0) {
                n /= 2;
            }
        }

        for (int i = 3; i * i <= N; i += 2) {
            if (n % i == 0) {
                factors.add(i);
                while (n % i == 0) {
                    n /= i;
                }
            }
            if (n == 1) {
                break;
            }
        }

        if (n > 1) {
            factors.add(n);
        }

        return factors.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
