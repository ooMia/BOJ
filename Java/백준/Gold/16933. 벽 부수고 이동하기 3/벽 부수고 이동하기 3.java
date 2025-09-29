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

    // 1. visited 배열을 3차원으로 변경: [부순 벽의 수][행][열]
    final int[][][] visited;
    final int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Solution(int nRows, int nCols, String[] grid, int limitBreak) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.grid = grid;
        this.limitBreak = limitBreak;

        // K+1개의 방문 기록 배열 생성
        this.visited = new int[limitBreak + 1][nRows][nCols];
    }

    public int solution() {
        // 4. BFS를 위해 일반 Queue 사용
        Queue<Point> q = new LinkedList<>();

        // 시작점: (0,0), 거리 1, 부순 벽 0개
        q.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = 1; // visited 배열에 거리 기록

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y == nRows - 1 && p.x == nCols - 1) {
                return p.dist;
            }

            // 낮/밤 구분: 거리가 홀수이면 낮, 짝수이면 밤
            boolean isDay = (p.dist % 2 == 1);
            int newDist = p.dist + 1;

            for (var dir : directions) {
                int newY = p.y + dir[0];
                int newX = p.x + dir[1];

                if (newY < 0 || nRows <= newY || newX < 0 || nCols <= newX) {
                    continue;
                }

                // 경우 1: 벽이 아닌 곳으로 이동
                if (grid[newY].charAt(newX) == '0') {
                    // 아직 방문하지 않았다면
                    if (visited[p.nBreak][newY][newX] == 0) {
                        visited[p.nBreak][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, p.nBreak));
                    }
                }
                // 경우 2: 벽을 만남
                else {
                    // 낮이고, 벽을 더 부술 수 있고, 해당 상태로 방문한 적 없다면
                    if (isDay && p.nBreak < limitBreak && visited[p.nBreak + 1][newY][newX] == 0) {
                        visited[p.nBreak + 1][newY][newX] = newDist;
                        q.offer(new Point(newY, newX, newDist, p.nBreak + 1));
                    }
                }
            }

            // 2. 경우 3: 밤에 벽을 부수기 위해 제자리에서 대기
            // isDay가 false(밤)일 때, 제자리에서 머무는 경우를 큐에 추가
            // 이 상태는 아직 방문 처리되지 않았어야 함
            if (!isDay && visited[p.nBreak][p.y][p.x] <= p.dist) {
                 visited[p.nBreak][p.y][p.x] = newDist;
                 q.offer(new Point(p.y, p.x, newDist, p.nBreak));
            }
        }
        return -1;
    }

    // Point 클래스는 내부 필드만 가지도록 단순화
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