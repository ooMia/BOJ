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

class Solution {

    final int nRows, nCols;
    final int[][] cumuls;

    Solution(int nRows, int nCols, int[][] numbers) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.cumuls = new int[nRows + 1][nCols + 1];

        for (int r = 1; r <= nRows; ++r) {
            for (int c = 1; c <= nCols; ++c) {
                cumuls[r][c] = cumuls[r - 1][c] + cumuls[r][c - 1] + numbers[r - 1][c - 1] - cumuls[r - 1][c - 1];
            }
        }
    }

    public int solution(int y1, int x1, int y2, int x2) {
        // sD(y2, x2) - sC(y1-1, x2) - sB(y2, x1-1) + sA(y1-1, x1-1)
        return cumuls[y2][x2] - cumuls[y1 - 1][x2] - cumuls[y2][x1 - 1] + cumuls[y1 - 1][x1 - 1];
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols;
    final int[][] numbers;

    final int K;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            nRows = input[0];
            nCols = input[1];
            numbers = new int[nRows][];
            for (int r = 0; r < nRows; r++) {
                numbers[r] = reader.readInts();
            }
            K = reader.readInts()[0];

            sb.ensureCapacity(20);
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
        var sol = new Solution(nRows, nCols, numbers);
        for (int k = 0; k < K; ++k) {
            var xy = reader.readInts();
            var res = sol.solution(xy[0], xy[1], xy[2], xy[3]);
            sb.append(res).append('\n');
        }
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
