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
    // 숫자 n으로 시작하고, 길이가 m인 자연수 중 계단 수에 해당하는 수의 개수
    long[][] dp = new long[10][100 + 1];

    int mod = 1_000_000_000;

    Solution() {
        // 한 자리수는 모두 계단 수
        for (int i = 1; i <= 9; i++) {
            dp[i][1] = 1;
        }
    }

    long solution(int N) {
        if (N == 1)
            return 9;

        for (int len = 2; len <= N; ++len) {
            for (int num = 0; num <= 9; ++num) {
                if (num == 0) {
                    dp[num][len] = dp[num + 1][len - 1] % mod;
                } else if (num == 9) {
                    dp[num][len] = dp[num - 1][len - 1] % mod;
                } else {
                    dp[num][len] = (dp[num - 1][len - 1] + dp[num + 1][len - 1]) % mod;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i <= 9; ++i)
            ans = (ans + dp[i][N]) % mod;
        return ans;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());

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
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public int[] readInts() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
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

    public int[][] readIntArrays() throws IOException {
        return br.lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}