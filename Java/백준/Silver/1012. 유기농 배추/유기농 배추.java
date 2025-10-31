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
            int T = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < T; i++) {
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

    final int nX, nY, K; // K: number of elements
    final boolean[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _xyk = reader.readInts();
            nX = _xyk[0];
            nY = _xyk[1];
            K = _xyk[2];
            grid = new boolean[nY][nX];
            sb.ensureCapacity(20);

            for (int i = 0; i < K; ++i) {
                var xy = reader.readInts();
                grid[xy[1]][xy[0]] = true;
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
        int count = 0;
        for (int y = 0; y < nY; ++y) {
            for (int x = 0; x < nX; ++x) {
                if (grid[y][x]) {
                    search(y, x);
                    count++;
                }
            }
        }
        sb.append(count).append('\n');
    }

    private void search(int y, int x) {
        if (y < 0 || y >= nY || x < 0 || x >= nX || !grid[y][x]) {
            return;
        }

        grid[y][x] = false; // Mark as visited

        search(y - 1, x); // Up
        search(y + 1, x); // Down
        search(y, x - 1); // Left
        search(y, x + 1); // Right
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
