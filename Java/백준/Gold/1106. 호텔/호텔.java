import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = 1;
            for (int i = 0; i < T; ++i) {
                IRunner runner = new Runner(br, bw);
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
    final int C; // C: promotion goal
    final int N; // N: number of promotions
    final int[][] promotions; // [0]: cost, [1]: benefit
    final int maxBenefit;

    public Solution(int C, int N, int[][] promotions) {
        this.C = C;
        this.N = N;
        this.promotions = promotions;
        int benefit = Integer.MIN_VALUE;
        for (var p : promotions) {
            benefit = Math.max(benefit, p[1]);
        }
        this.maxBenefit = benefit;
    }

    public int solution() {
        int limit = C + maxBenefit;
        int[] dp = new int[limit + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (var p : promotions) {
            var cost = p[0];
            var benefit = p[1];
            for (int i = benefit; i <= limit; ++i) {
                if (dp[i - benefit] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - benefit] + cost);
                }
            }
        }

        // [C, limit] 구간 내 최소 비용 반환
        int res = Integer.MAX_VALUE;
        for (int i = C; i <= limit; ++i) {
            res = Math.min(res, dp[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int C, N; // C: promotion goal
    final int[][] promotions; // [0]: cost, [1]: benefit

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.C = input[0];
            this.N = input[1];
            this.promotions = new int[N][];
            for (int i = 0; i < N; ++i) {
                this.promotions[i] = reader.readInts();
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        var sol = new Solution(C, N, promotions);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}

class Reader implements IReader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void skipLine() throws IOException {
        br.readLine();
    }

    @Override
    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    @Override
    public String line() throws IOException {
        return br.readLine();
    }

    @Override
    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    @Override
    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
