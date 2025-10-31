import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    final int[][][] visited; // [부순 벽의 수][행][열] = 거리
    final int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Solution(int nRows, int nCols, String[] grid, int limitBreak) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.limitBreak = limitBreak;

        this.visited = new int[limitBreak + 1][nRows][nCols];
    }

    public int solution() {
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y == nRows - 1 && p.x == nCols - 1) {
                return p.dist;
            }

            int newDist = p.dist + 1;

            for (var dir : directions) {
                int newY = p.y + dir[0];
                int newX = p.x + dir[1];

                if (newY < 0 || nRows <= newY || newX < 0 || nCols <= newX)
                    continue; // 좌표 유효성 판단

                if (grid[newY].charAt(newX) == '0') { // 경우 1: 벽이 아닌 곳으로
                    if (!isVisited(p.nBreak, newY, newX)) {
                        visited[p.nBreak][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, p.nBreak));
                    }
                }
                // 경우 2: 벽을 만남
                else {
                    var newBreak = p.nBreak + 1;
                    // 벽을 더 부술 수 있고, 해당 상태로 방문한 적 없다면
                    if (newBreak <= limitBreak && !isVisited(newBreak, newY, newX)) {
                        visited[newBreak][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, newBreak));
                    }
                }
            }
        }
        return -1;
    }

    boolean isVisited(int nBreak, int y, int x) {
        return visited[nBreak][y][x] > 0;
    }

    class Point {
        final int y, x, dist, nBreak;

        Point(int y, int x, int dist, int nBreak) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.nBreak = nBreak;
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nRows, nCols;
    final String[] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.nRows = input[0];
            this.nCols = input[1];

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
        var sol = new Solution(nRows, nCols, grid, 1);
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