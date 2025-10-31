import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
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

    final int N; // grid size: 2^N * 2^N
    final int r, c; // dest coords
    final Stack<Board> stack = new Stack<>();

    Solution(int N, int r, int c) {
        this.N = N;
        this.r = r;
        this.c = c;
        stack.push(new Board(0, 0, 1 << N));
    }

    public int solution() {
        int count = 0;
        while (!stack.isEmpty()) {
            var board = stack.pop();
            if (!board.contains(r, c)) {
                count += board.size();
            } else if (board.len > 1) {
                stack.addAll(board.split());
            } else {
                break;
            }
        }
        return count;
    }

    class Board {
        final int minX, minY, len;

        Board(int minX, int minY, int len) {
            this.minX = minX;
            this.minY = minY;
            this.len = len;
        }

        boolean contains(int r, int c) {
            return minX <= c && c < minX + len && minY <= r && r < minY + len;
        }

        int size() {
            return len * len;
        }

        Collection<? extends Solution.Board> split() {
            int half = len / 2;
            return List.of( // reversed Z-order
                    new Board(minX + half, minY + half, half),
                    new Board(minX, minY + half, half),
                    new Board(minX + half, minY, half),
                    new Board(minX, minY, half));
        }
    }

}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, r, c;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nrc = reader.readInts();
            this.N = nrc[0];
            this.r = nrc[1];
            this.c = nrc[2];

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
        var sol = new Solution(N, r, c);
        var res = sol.solution();
        sb.append(res).append('\n');
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
