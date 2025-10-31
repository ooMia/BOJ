import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < T; ++i) {
                long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                long end = System.currentTimeMillis();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    long solution(String N) {
        // 무식하게 구현해서 적당한 N에 대해 답을 구해보니 보이는 것
        // 1. N의 자리수가 클수록 결과가 커진다.
        //    왜냐하면 자리수가 변경되는 시점의 모든 수가 0이 아니기 떄문.
        //    예를 들어, 999 * 0 = 0이지만, 애초에 999까지 오려면 [100, 998] 모두를 포함해야 한다.
        // 2. n + toggle(n) = (해당 자리수의 9로 이루어진 수)

        // 2에서 두 자연수의 합이 일정하다는 것에 착안,
        // 3. 자연수 x에 대해 toggle(x) = y라 하면,
        //    산술-기하평균 부등식에 따라 x + y >= 2 * sqrt(xy)이 성립하며, x = y일 때 등호가 성립한다.

        // 정리하면
        // 1. 자리수가 가장 큰 수의 집합에서
        // 2. 해당 자리수의 최댓값 / 2에 가장 가까운 수 k에 대해
        // 3. k * toggle(k)가 최댓값이 된다.

        long target = Math.min(findMaximumMiddleNumber(N), Long.parseLong(N));
        return target * toggle(String.valueOf(target));
    }

    private long findMaximumMiddleNumber(String N) {
        // N = 10이면 99/2
        // N = 123이면 999/2 = 499
        int nDigit = N.length();
        long maxMiddle = (long) Math.pow(10, nDigit) - 1;
        return maxMiddle / 2;
    }

    private long toggle(String n) {
        // n의 각 자리 수 a에 대해 그 수를 (9 - a)로 바꾼 수를 반환
        long res = 0;
        for (int i = 0; i < n.length(); ++i) {
            int digit = n.charAt(i) - '0';
            int toggled = 9 - digit;
            res = res * 10 + toggled;
        }
        return res;
    }
}

class Runner {
    final BufferedWriter bw;

    final String N;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        // var reader = new Reader(br);
        try {
            N = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        var res = sol.solution(N);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}