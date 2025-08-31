import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
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

    final char[][] grid;

    Solution(char[][] grid) {
        this.grid = grid;
    }

    public String solution() {
        PriorityQueue<Paper> queue = new PriorityQueue<>();
        queue.add(new Paper(0, 0, grid.length));

        int nWhite = 0, nBlue = 0;
        while (!queue.isEmpty()) {
            Paper paper = queue.poll();
            int color = paper.color();

            if (color == -1) { // mixed
                if (paper.size == 2) {
                    int Y = paper.minY, X = paper.minX;
                    for (int y = Y; y < Y + 2; ++y) {
                        for (int x = X; x < X + 2; ++x) {
                            if (grid[y][x] == '0') {
                                ++nWhite;
                            } else {
                                ++nBlue;
                            }
                        }
                    }
                } else
                    queue.addAll(paper.split());
            } else if (color == 0) {
                ++nWhite;
            } else if (color == 1) {
                ++nBlue;
            }
        }
        return String.format("%d\n%d", nWhite, nBlue);
    }

    class Paper implements Comparable<Paper> {
        final int minY, maxY, minX, maxX, size;

        Paper(int minY, int minX, int size) {
            this.minY = minY;
            this.minX = minX;
            this.size = size;
            this.maxY = minY + size - 1;
            this.maxX = minX + size - 1;
        }

        int color() {
            // -1: mixed, 0: white, 1: blue
            char base = grid[minY][minX];
            for (int y = minY; y <= maxY; ++y) {
                for (int x = minX; x <= maxX; ++x) {
                    if (grid[y][x] != base)
                        return -1; // mixed
                }
            }
            return base - '0';
        }

        Collection<Paper> split() {
            int half = size / 2;
            return List.of(
                    new Paper(minY, minX, half),
                    new Paper(minY, minX + half, half),
                    new Paper(minY + half, minX, half),
                    new Paper(minY + half, minX + half, half));
        }

        @Override
        public int compareTo(Solution.Paper o) {
            return Integer.compare(this.size, o.size);
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final char[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            this.grid = new char[N][N];
            for (int r = 0; r < N; ++r) {
                var s = reader.line();
                for (int i = 0, c = 0; i < s.length(); i += 2)
                    this.grid[r][c++] = s.charAt(i);
            }

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
        var sol = new Solution(grid);
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
