import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = Integer.parseInt(br.readLine().trim());
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
    final boolean[] keys;

    final boolean[][] isInQueue;
    final List<Queue<int[]>> queues;

    final int[] dY = { -1, 1, 0, 0 };
    final int[] dX = { 0, 0, -1, 1 };

    private int count = 0;
    private boolean gotNewKey = true;

    Solution(int h, int w, String[] grid, boolean[] keys) {
        this.nRows = h;
        this.nCols = w;
        this.grid = grid;
        this.keys = keys;

        this.queues = List.of(new ArrayDeque<>(), new ArrayDeque<>());
        this.isInQueue = new boolean[nRows][nCols];
    }

    public int solution(boolean[] keys) {
        var currentQ = queues.get(1);
        var nextQ = queues.get(0);

        initQueue(nextQ); // 테두리를 탐색하여 벽이 아닌 모든 셀 큐에 추가

        // 2. BFS 탐색
        // 현재 큐에서 탐색을 진행하며
        // '통과하지 못한 문'은 다음 큐에 추가
        // 중간에 새로운 열쇠를 획득하면 기록하고
        // 현재 trial에 새로운 열쇠를 획득했고, 다음 큐가 남아 있는 경우에만 탐색을 지속

        while (gotNewKey && !nextQ.isEmpty()) {
            {
                var temp = currentQ;
                currentQ = nextQ;
                nextQ = temp;
                gotNewKey = false;
            }

            while (!currentQ.isEmpty()) {
                var pos = currentQ.poll();
                int y = pos[0], x = pos[1];
                char c = getCell(y, x);
                if (isAccessible(y, x)) {
                    handleCell(c);
                } else if (Character.isUpperCase(c)) {
                    addQueue(nextQ, y, x);
                    continue;
                }

                // 상하좌우 탐색
                for (int d = 0; d < 4; d++) {
                    int ny = y + dY[d];
                    int nx = x + dX[d];
                    if (isInBounds(ny, nx) && !isInQueue[ny][nx] && getCell(ny, nx) != '*') {
                        addQueue(currentQ, ny, nx);
                    }
                }
            }
        }

        return count;
    }

    boolean isInBounds(int y, int x) {
        return 0 <= y && y < nRows && 0 <= x && x < nCols;
    }

    char getCell(int y, int x) {
        return grid[y].charAt(x);
    }

    void initQueue(Queue<int[]> q) {
        // 테두리를 탐색하여 벽이 아닌 모든 셀 큐에 추가
        int yMin = 0, yMax = nRows - 1, xMin = 0, xMax = nCols - 1;
        for (int y = 0; y < nRows; ++y) {
            if (y == yMin || y == yMax) {
                for (int x = 0; x < nCols; ++x) {
                    char c = getCell(y, x);
                    if (c != '*')
                        addQueue(q, y, x);
                }
            } else {
                if (getCell(y, xMin) != '*')
                    addQueue(q, y, xMin);
                if (getCell(y, xMax) != '*')
                    addQueue(q, y, xMax);
            }
        }
    }

    private void addQueue(Queue<int[]> q, int y, int x) {
        q.add(new int[] { y, x });
        isInQueue[y][x] = true;
    }

    boolean isAccessible(int y, int x) {
        char cell = grid[y].charAt(x);
        if (cell == '*') {
            return false;
        }
        if (Character.isUpperCase(cell)) {
            return keys[cell - 'A'];
        }
        return true;
    }

    void handleCell(char c) {
        if ('$' == c)
            this.count += 1;
        else if (Character.isLowerCase(c)) {
            keys[c - 'a'] = true;
            gotNewKey = true;
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int h, w;
    final String[] grid;
    final boolean[] keys = new boolean['z' - 'a' + 1];

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _hw = reader.readInts();
            this.h = _hw[0];
            this.w = _hw[1];
            this.grid = new String[h];
            for (int i = 0; i < h; ++i) {
                this.grid[i] = reader.line();
            }
            var keyLine = reader.line();
            if ("0".equals(keyLine))
                return;
            for (char c : keyLine.toCharArray()) {
                keys[c - 'a'] = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution(h, w, grid, keys);
        var res = sol.solution(keys);
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
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
}