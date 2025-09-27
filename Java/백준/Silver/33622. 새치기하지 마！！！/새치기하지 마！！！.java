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
    private final double[] probs;
    private final int[][] choices;
    private final int N;

    Solution(int N) {
        this.N = N;
        int size = Math.max(4, N + 1);
        probs = new double[size];
        choices = new int[size][];

        probs[0] = 1.0;
        probs[1] = probability(1, 1);
        probs[2] = probability(2, 2);
        probs[3] = probability(3, 3);

        choices[0] = new int[]{};
        choices[1] = new int[]{1};
        choices[2] = new int[]{2};
        choices[3] = new int[]{3};

        for (int target = 1; target <= N; ++target) {
            for (int k = 1; k <= target; ++k) {
                double trial = probs[target - k] * probability(target, k);
                if (trial > probs[target]) {
                    probs[target] = trial;
                    int newSize = choices[target - k].length + 1;
                    choices[target] = new int[newSize];
                    System.arraycopy(choices[target - k], 0, choices[target], 0, newSize - 1);
                    choices[target][newSize - 1] = k;
                }
            }
        }
    }

    private static double probability(int n, int k) {
        return 1.0 - (double) k / (n + 1);
    }

    String solution() {
        int X = choices[N].length;
        var history = choices[N];

        var sb = new StringBuilder();
        sb.append(X).append('\n');
        for (int hist : history) {
            sb.append(hist).append(' ');
        }
        return sb.toString().trim();
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
        var sol = new Solution(N);
        var res = sol.solution();
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