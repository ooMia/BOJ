import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
    final int N;
    final List<int[]> monitors;

    Solution(int N, List<int[]> monitors) {
        this.N = N;
        this.monitors = monitors;
    }

    public String solution() {
        Collections.sort(monitors, (o1, o2) -> {
            var cmp = Integer.compare(diagSq(o2), diagSq(o1));
            if (cmp != 0)
                return cmp;
            return Integer.compare(o1[2], o2[2]);
        });
        var sb = new StringBuilder();
        for (var monitor : monitors) {
            sb.append(monitor[2]).append('\n');
        }
        return sb.toString();
    }

    int diagSq(int[] monitor) {
        return monitor[0] * monitor[0] + monitor[1] * monitor[1];
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final List<int[]> monitors;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.monitors = new ArrayList<>(N);
            for (int n = 0, id = 1; n < N; ++n, ++id) {
                var wh = reader.readInts();
                this.monitors.add(new int[] { wh[0], wh[1], id });
            }
        } catch (IOException e) {
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
        var res = new Solution(N, monitors).solution();
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
}