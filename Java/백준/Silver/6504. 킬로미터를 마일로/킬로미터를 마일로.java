import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            // int T = Integer.parseInt(br.readLine().trim());
            // for (int i = 0; i < T; ++i)
            {
                // System.err.println("\ncase " + (i + 1));
                // long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                // long end = System.currentTimeMillis();
                // System.err.println("took " + (end - start) + " ms");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {
    private static final int MAX_VALUE = 25_000;
    int[] fibos = new int[21]; // 1 ~ 17711 F(21)

    Solution() {
        fibos[0] = 1;
        fibos[1] = 2;
        for (int i = 2; i < fibos.length; i++) {
            fibos[i] = fibos[i - 1] + fibos[i - 2];
        }
    }

    public int solution(int cases) {
        int sum = 0;
        for (int i = firstIndex(cases); cases > 0 && i >= 1; i--) {
            var number = fibos[i];
            if (number > cases) {
                continue;
            }
            sum += fibos[i - 1];
            cases -= number;
        }
        return sum;
    }

    private int firstIndex(int value) {
        int idx = Arrays.binarySearch(fibos, value);
        if (idx < 0) {
            idx = -(idx + 1) - 1;
        }
        return Math.max(0, idx);
    }

    // x kilometers to y miles
}

class Runner {
    final BufferedWriter bw;

    final IntStream cases;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        // var reader = new Reader(br);
        try {
            br.readLine();
            cases = br.lines().mapToInt(Integer::parseInt);
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
        cases.forEach(value -> {
            var res = sol.solution(value);
            _write(res);
            _write('\n');
        });
        // var res = sol.solution(cases);
        // _write(res);
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

    public int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int[] readInts() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }

    public long[] readLongs() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }
}