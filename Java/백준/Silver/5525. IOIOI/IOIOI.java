import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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

    final int N, M;
    final String S;

    Solution(int N, int M, String S) {
        this.N = N;
        this.M = M;
        this.S = S;
    }

    public int solution() {
        var ioiBuilder = new StringBuilder("IOI");
        for (int i = 1; i < N; ++i) {
            ioiBuilder.append("OI");
        }
        var ioi = ioiBuilder.toString();
        var ioiLength = ioi.length();

        int count = 0;
        for (int i = 0; i <= M - ioiLength; ++i) {
            var comp = S.substring(i, i + ioiLength);
            if (comp.equals(ioi)) {
                count++;
            }
        }
        return count;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final String S;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.valueOf(reader.line());
            this.M = Integer.valueOf(reader.line());
            this.S = reader.line();

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
        var sol = new Solution(N, M, S);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public String line() throws IOException {
        return br.readLine();
    }
}