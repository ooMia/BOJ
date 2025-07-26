import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int A, B;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ab = reader.readInts();
            A = _ab[0];
            B = _ab[1];
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
        Integer res = solve(A, B, 1);
        res = res == null ? -1 : res;
        sb.append(res).append('\n');
    }

    private Integer solve(long a, long b, int cnt) {
        // operation 1. multiply by 2
        // operation 2. multiply by 10 and add 1
        if (b < a) {
            return null;
        }
        if (a == b) {
            return cnt;
        }
        Integer case1 = solve(a * 2, b, cnt + 1);
        if (case1 == null) {
            case1 = Integer.MAX_VALUE;
        }
        Integer case2 = solve(a * 10 + 1, b, cnt + 1);
        if (case2 == null) {
            case2 = Integer.MAX_VALUE;
        }

        int res = Math.min(case1, case2);
        if (res == Integer.MAX_VALUE) {
            return null;
        }
        return res;
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
