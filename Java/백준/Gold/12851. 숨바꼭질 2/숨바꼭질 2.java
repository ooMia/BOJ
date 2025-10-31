import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    final int N, K; // N, K: location of A and B; 0 <= N, K <= 10^6
    final int[] dp;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nk = reader.readInts();
            N = _nk[0];
            K = _nk[1];
            dp = new int[100_001];
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
        Queue<Data> q = new LinkedList<>();
        q.offer(new Data(N, 0));

        while (!q.isEmpty()) {
            Data current = q.poll();
            if (current.position == K) {
                sb.append(current.nMove).append('\n');
                int nAnswer = 1;
                while (!q.isEmpty()) {
                    Data next = q.poll();
                    if (next.position == K && next.nMove == current.nMove) {
                        nAnswer++;
                    }
                }
                sb.append(nAnswer).append('\n');
                return;
            }

            int[] modifier = { 1, -1, 2 };
            for (int mod : modifier) {
                int next = mod == 2 ? current.position * 2 : current.position + mod;
                if (next < 0 || 100_000 < next) {
                    continue;
                }
                if (dp[next] == 0 || current.nMove + 1 <= dp[next]) {
                    dp[next] = current.nMove + 1;
                    q.add(new Data(next, current.nMove + 1));
                }
            }
        }
    }
}

class Data {
    int position;
    int nMove;

    Data(int position, int nMove) {
        this.position = position;
        this.nMove = nMove;
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
