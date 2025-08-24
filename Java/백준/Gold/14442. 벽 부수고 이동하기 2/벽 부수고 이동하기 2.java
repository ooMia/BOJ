import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
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
    final int nRows, nCols;
    final String[] grid;
    final int limitBreak;

    final byte[][] visited;

    public Solution(int nRows, int nCols, String[] grid, int limitBreak) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.limitBreak = limitBreak;

        this.visited = new byte[nRows][nCols];
        for (int i = 0; i < nRows; ++i) {
            Arrays.fill(visited[i], (byte) -1);
        }
    }

    public int solution() {
        var pq = new PriorityQueue<Point>((o1, o2) -> {
            if (o1.dist != o2.dist)
                return Integer.compare(o1.dist, o2.dist);
            return Integer.compare(o1.nBreak, o2.nBreak);
        });
        pq.offer(new Point(0, 0, 1, 0));

        while (!pq.isEmpty()) {
            var p = pq.poll();

            if (p.y == nRows - 1 && p.x == nCols - 1)
                return p.dist;

            pq.addAll(p.neighbors());
        }
        return -1;
    }

    class Point {
        final int y, x, dist;
        final byte nBreak;

        Point(int y, int x, int dist, int nBreak) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.nBreak = (byte) nBreak;
        }

        List<Point> neighbors() {
            var newDist = dist + 1;
            var directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
            var points = new ArrayList<Point>();
            for (var dir : directions) {
                var newY = y + dir[0];
                var newX = x + dir[1];
                if (newY < 0 || nRows <= newY || newX < 0 || nCols <= newX)
                    continue;
                var newBreak = nBreak + (grid[newY].charAt(newX) == '1' ? 1 : 0);
                if (limitBreak < newBreak || visited[newY][newX] != -1 && visited[newY][newX] <= newBreak)
                    continue;
                points.add(new Point(newY, newX, newDist, newBreak));
                visited[newY][newX] = (byte) newBreak;
            }
            return points;
        }

        @Override
        public String toString() {
            return String.format("Point [y=%s, x=%s, dist=%s, nBreak=%s]", y, x, dist, nBreak);
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols, K;
    final String[] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.nRows = input[0];
            this.nCols = input[1];
            this.K = input[2];

            this.grid = new String[nRows];
            for (int i = 0; i < nRows; ++i) {
                grid[i] = reader.line();
            }

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
        var sol = new Solution(nRows, nCols, grid, K);
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

    public int[][] readIntArrays() throws IOException {
        return lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}