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

    char[][] grid = new char[9][9];
    boolean[][] rowUsed = new boolean[9][10];
    boolean[][] colUsed = new boolean[9][10];
    boolean[][] boxUsed = new boolean[9][10];
    List<int[]> blanks = new ArrayList<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            for (int y = 0; y < 9; ++y) {
                grid[y] = reader.line().toCharArray();
                for (int x = 0; x < 9; ++x) {
                    int num = grid[y][x] - '0';
                    if (num == 0) {
                        blanks.add(new int[] { y, x });
                    } else {
                        int box = (y / 3) * 3 + (x / 3);
                        rowUsed[y][num] = true;
                        colUsed[x][num] = true;
                        boxUsed[box][num] = true;
                    }
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
    public void run() {
        solve(0);
        for (int y = 0; y < 9; ++y) {
            for (int x = 0; x < 9; ++x) {
                sb.append(grid[y][x]);
            }
            sb.append('\n');
        }
    }

    private boolean solve(int idx) {
        if (idx == blanks.size())
            return true;
        int y = blanks.get(idx)[0], x = blanks.get(idx)[1];
        int box = (y / 3) * 3 + (x / 3);
        for (int num = 1; num <= 9; ++num) {
            if (!rowUsed[y][num] && !colUsed[x][num] && !boxUsed[box][num]) {
                grid[y][x] = (char) ('0' + num);
                rowUsed[y][num] = colUsed[x][num] = boxUsed[box][num] = true;
                if (solve(idx + 1))
                    return true;
                grid[y][x] = '0';
                rowUsed[y][num] = colUsed[x][num] = boxUsed[box][num] = false;
            }
        }
        return false;
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

    void flush() throws IOException;
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
