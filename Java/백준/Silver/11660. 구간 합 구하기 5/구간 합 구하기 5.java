import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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

    final int N, M;
    final int[][] cumuls; // (0, 0)부터 (i, j)까지의 합

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var input = reader.readInts();
            N = input[0];
            M = input[1];
            sb.ensureCapacity(M * 10);

            // (N+1) x (N+1) 크기로 설정, 0번째 행/열은 패딩
            cumuls = new int[N + 1][N + 1];

            for (int iy = 1; iy <= N; ++iy) {
                int[] numbers = reader.readInts();
                for (int ix = 1; ix <= N; ++ix) {
                    cumuls[iy][ix] = cumuls[iy - 1][ix] + cumuls[iy][ix - 1]
                            - cumuls[iy - 1][ix - 1] + numbers[ix - 1];
                }
            }
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
        for (int i = 0; i < M; ++i) {
            var inputs = reader.readInts();
            int x1 = inputs[0], y1 = inputs[1], x2 = inputs[2], y2 = inputs[3];
            sb.append(sum(x1, y1, x2, y2)).append('\n');
        }
    }

    private int sum(int x1, int y1, int x2, int y2) {
        // 1-based 인덱스를 그대로 사용, 경계 체크 불필요
        return cumuls[x2][y2] - cumuls[x1 - 1][y2]
                - cumuls[x2][y1 - 1] + cumuls[x1 - 1][y1 - 1];
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
