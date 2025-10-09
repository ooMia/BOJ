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
    final int MOD = 1_000_000_007;
    final int[] dp = new int[191_229 + 1];

    Solution() {
        dp[1] =1;
        dp[2] =2;
        for (int i = 3; i <= 191_229; ++i) {
            long temp = (long) dp[i - 1] + dp[i - 2];
            dp[i] = (int) (temp % MOD);
        }
    }

    public int solution(int n) {
        return dp[n];
    }
}

class Runner {
    final BufferedWriter bw;
    final BufferedReader br;

    final int T;
    final int[] ns;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        this.br = br;

        // var reader = new Reader(br);
        try {
            T = Integer.parseInt(br.readLine());
            ns = br.lines().mapToInt(Integer::parseInt).toArray();

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
        for (var n : ns) {
            var res = sol.solution(n);
            _write(String.format("%d\n", res));
        }
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