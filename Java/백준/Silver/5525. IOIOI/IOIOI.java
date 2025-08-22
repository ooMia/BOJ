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
    final int N;
    final String ioi;

    final StringBuilder sb = new StringBuilder();
    int count = 0;

    Solution(int N, String ioi) {
        this.N = N;
        this.ioi = ioi;
    }

    public int solution() {
        for (char c : ioi.toCharArray()) {
            append(c);
        }
        flush();
        return count;
    }

    void append(char c) {
        var n = sb.length();
        if ((n == 0 && c == 'I') || (n > 0 && sb.charAt(n - 1) != c))
            sb.append(c);
        else {
            flush();
            if (c == 'I')
                sb.append(c);
        }
    }

    void flush() {
        var k = (sb.length() - 1) / 2;
        if (N <= k)
            count += k - N + 1;
        sb.setLength(0);
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final String ioi;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(reader.line());
            reader.skipLine();
            this.ioi = reader.line();

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
        var sol = new Solution(N, ioi);
        var res = sol.solution();
        sb.append(res).append('\n');
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
}