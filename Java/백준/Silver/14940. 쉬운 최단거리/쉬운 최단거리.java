import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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

    final int nRows, nCols;
    final char[][] grid;
    final boolean[][] visited;
    final int[][] dist;

    final PriorityQueue<Point> queue = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1.dist, o2.dist));

    Solution(int nRows, int nCols, char[][] grid, int rDest, int cDest) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.visited = new boolean[nRows][nCols];
        this.dist = new int[nRows][nCols];

        queue.add(new Point(rDest, cDest, 0));
        visited[rDest][cDest] = true;
    }

    public String solution() {
        while (!queue.isEmpty()) {
            var p = queue.poll();
            dist[p.y][p.x] = p.dist;

            var nexts = List.of(
                    new Point(p.y, p.x - 1, p.dist + 1), // left
                    new Point(p.y, p.x + 1, p.dist + 1), // right
                    new Point(p.y - 1, p.x, p.dist + 1), // down
                    new Point(p.y + 1, p.x, p.dist + 1) // up
            );
            for (var next : nexts) {
                int Y = next.y, X = next.x;
                if (isValidPos(Y,X) && visited[Y][X] == false && grid[Y][X] == '1') {
                    visited[Y][X] = true;
                    queue.add(next);
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                int d = (grid[row][col] == '1' && dist[row][col] == 0) ? -1 : dist[row][col];
                sb.append(d).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    boolean isValidPos(int y, int x) {
        return 0 <= y && y < nRows && 0 <= x && x < nCols;
    }

    class Point {
        final int y, x, dist;

        Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols;
    int rDest, cDest;
    final char[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var rc = reader.readInts();
            this.nRows = rc[0];
            this.nCols = rc[1];
            this.grid = new char[nRows][nCols];
            for (int row = 0; row < nRows; ++row) {
                var s = reader.line();
                for (int i = 0, col = 0; i < s.length(); i += 2) {
                    var c = s.charAt(i);
                    if (c == '2') {
                        this.rDest = row;
                        this.cDest = col;
                    }
                    this.grid[row][col++] = c;
                }
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
        var sol = new Solution(nRows, nCols, grid, rDest, cDest);
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
