import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
    final char[][] grid;

    final int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    final int[][] groupId;
    final int[][] length;

    public Solution(int nRows, int nCols, String[] grid) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = new char[nRows][];
        for (int i = 0; i < nRows; i++) {
            this.grid[i] = grid[i].toCharArray();
        }

        this.groupId = new int[nRows][nCols];
        this.length = new int[nRows][nCols];
    }

    public String solution() {
        var ones = new ArrayList<Point>();

        // 방문하지 않은 빈 칸 '0'을 기점으로
        // 방문 가능한 빈 칸을 모두 배열에 추가하고
        // 동일한 groupId와 배열의 길이 값을 length에 부여하기
        int id = 1;
        for (int y = 0; y < nRows; ++y)
            for (int x = 0; x < nCols; ++x)
                if (grid[y][x] == '1')
                    ones.add(new Point(y, x));
                else if (groupId[y][x] == 0)
                    init(new Point(y, x), id++);

        // 모든 벽 '1'을 기점으로
        // 4개의 방향에 존재하는 length 값을 모두 더한 값에 다시 1을 더하고 mod 10
        // 각 방향의 length 값이 동일한 groupId로부터 얻어지지 않도록 주의
        for (var p : ones) {
            var set = new HashMap<Integer, Integer>();
            for (var dir : directions) {
                int newY = p.y + dir[0], newX = p.x + dir[1];
                if (newY < 0 || newY >= nRows || newX < 0 || newX >= nCols)
                    continue;
                set.put(groupId[newY][newX], length[newY][newX]);
            }

            int sum = 0;
            for (int value : set.values())
                sum += value;
            grid[p.y][p.x] = (char) (((sum + 1) % 10) + '0');
        }

        // 문자열을 이어붙여 반환
        var sb = new StringBuilder(nRows * (nCols + 1));
        for (int y = 0; y < nRows; ++y) {
            sb.append(grid[y]);
            sb.append('\n');
        }
        return sb.toString();
    }

    void init(Point startPoint, int id) {
        // 시작점을 기준으로 4개의 방향을 탐색하며 벽이 아닌 빈 칸을 모두 배열에 추가
        // 방문 여부는 id 부여 여부로 판단
        // 모든 탐색이 종료된 이후에는 전체 Point를 순회하며 length 초기화
        var visited = new ArrayList<Point>();

        var q = new LinkedList<Point>();
        q.add(startPoint);
        assignId(visited, startPoint, id);

        while (!q.isEmpty()) {
            var curr = q.poll();

            for (var dir : directions) {
                int newY = curr.y + dir[0], newX = curr.x + dir[1];
                if (newY < 0 || newY >= nRows || newX < 0 || newX >= nCols)
                    continue;
                if (grid[newY][newX] == '1' || groupId[newY][newX] != 0)
                    continue;

                var p = new Point(newY, newX);
                q.add(p);
                assignId(visited, p, id);
            }
        }

        for (var p : visited)
            length[p.y][p.x] = visited.size();
    }

    void assignId(List<Point> l, Point p, int id) {
        l.add(p);
        groupId[p.y][p.x] = id;
    }

    class Point {
        final int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
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
                this.grid[i] = reader.line();
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
        var sol = new Solution(nRows, nCols, grid);
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