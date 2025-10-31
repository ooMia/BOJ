import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
    public String solution(int a, int b) {
        return a > b ? "Yes" : "No";
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int[][] pairs;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.pairs = reader.readIntArrays();

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution();
        for (int i = 0; i < pairs.length - 1; ++i) {
            var res = sol.solution(pairs[i][0], pairs[i][1]);
            sb.append(res).append('\n');
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public void skipLine() throws IOException {
        br.readLine();
    }

    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    public String line() throws IOException {
        return br.readLine();
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

    public List<Integer> readIntegers() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        List<Integer> res = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; ++i) {
            res.add(Integer.parseInt(st.nextToken()));
        }
        return res;
    }

    public int[][] readIntArrays() throws IOException {
        return lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}