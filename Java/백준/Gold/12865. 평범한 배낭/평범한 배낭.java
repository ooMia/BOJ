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
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        try {
            IRunner runner = new Runner(br, bw);
            runner.run();
            runner.flush();

            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K; // N: total items, K: weight limit
    final int[] weights; // weights[i] = weight of item i
    final int[] values; // values[i] = value of item i
    final int[][] dp; // dp[w][i] = max value with weight w using items after i

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nk = reader.readInts();
            N = _nk[0];
            K = _nk[1];
            weights = new int[N];
            values = new int[N];
            for (int i = 0; i < N; ++i) {
                int[] item = reader.readInts();
                weights[i] = item[0];
                values[i] = item[1];
            }
            dp = new int[K + 1][N + 1];
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
        pack(K, 0);
        sb.append(dp[K][0]).append('\n');
    }

    private int pack(int w, int i) {
        // 기저 사례
        // 1. 아이템을 더 이상 가져갈 수 없는 경우
        if (i >= N || w < 1) {
            return dp[w][i] = 0;
        }
        // 2. 현재 아이템을 가져갈 수 없는 경우
        if (weights[i] > w) {
            return dp[w][i] = pack(w, i + 1);
        }
        // 3. 이전에 계산한 값이 있는 경우
        if (dp[w][i] > 0) {
            return dp[w][i];
        }

        // 해당 아이템을 가져가는 경우
        var a = pack(w - weights[i], i + 1) + values[i];
        // 해당 아이템을 가져가지 않는 경우
        var b = pack(w, i + 1);
        return dp[w][i] = Math.max(a, b);
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
