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
            int T = 1;
            for (int i = 0; i < T; ++i) {
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {
    // 두 자연수 A, B가 주어졌을 때,
    // A ≤ x ≤ B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때
    // 1의 개수의 합을 구하는 프로그램을 작성하시오.

    // 2^x <= k < 2^(x+1)를 만족하는 모든 k를 이진수로 표현했을 때 1의 개수의 합
    long dp[] = new long[54 + 1]; // log_2(10^16) < 54

    Solution() {
        // 주어진 비트가 1개면 dp[0] = 1
        // 주어진 비트가 2개면 dp[1] = 2^1 + 2 * dp[0]
        // 주어진 비트가 3개면 dp[2] = 2^2 + 2 * dp[1]
        dp[0] = 1;
        for (int bit = 1; bit <= 54; ++bit) {
            dp[bit] = (1L << bit) + 2 * dp[bit - 1];
        }
    }

    /**
     * A부터 B까지의 모든 수에 대해 이진수 표현 시 1의 개수 총합을 구합니다.
     * 
     * @param A 시작 자연수
     * @param B 끝 자연수
     * @return 1의 개수 총합
     */
    long solution(long A, long B) {
        return countSetBits(B) - countSetBits(A - 1);
    }

    /**
     * 0부터 n까지의 모든 수에 대해 이진수 표현 시 1의 개수 총합을 구합니다.
     * 
     * @param n 끝 자연수
     * @return 1의 개수 총합
     */
    long countSetBits(long n) {
        if (n <= 0) {
            return 0;
        }

        // n의 최상위 비트(MSB) 위치를 찾습니다. (0-indexed)
        // 예를 들어 n=13 (1101)이면, msbPos = 3
        int msbPos = getMsbPosition(n);

        // 1. [0, 2^msbPos - 1] 구간의 1의 개수
        // dp 배열의 정의에 따라 dp[msbPos-1] 입니다.
        long count = (msbPos > 0) ? dp[msbPos - 1] : 0;

        // 2. [2^msbPos, n] 구간의 최상위 비트(MSB)에 대한 1의 개수
        // 이 구간의 모든 수는 msbPos 위치에 1을 가집니다.
        // 구간의 숫자 개수는 (n - 2^msbPos + 1) 입니다.
        long remainder = n - (1L << msbPos);
        count += (remainder + 1);

        // 3. [2^msbPos, n] 구간에서 MSB를 제외한 나머지 비트들의 1의 개수
        // 이는 [0, remainder] 구간의 1의 개수와 같습니다.
        // 재귀적으로 계산합니다.
        count += countSetBits(remainder);

        return count;
    }

    int getMsbPosition(long n) {
        int pos = 0;
        // 1L을 계속 왼쪽으로 시프트하면서 n보다 커지기 직전까지의 위치를 찾습니다.
        while ((1L << (pos + 1)) <= n) {
            pos++;
        }
        return pos;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final long A, B;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var line = reader.readLongs();
            A = line[0];
            B = line[1];

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
        var res = sol.solution(A, B);
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public long[] readLongs() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }
}