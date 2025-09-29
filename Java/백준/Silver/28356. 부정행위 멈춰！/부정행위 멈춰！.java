import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

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
    public String solution(int nRows, int nCols) {
        int K = 0;
        int[][] grid = new int[nRows][nCols];
        while (fillGrid(++K, grid));
        return toResult(K - 1, grid);
    }

    private boolean fillGrid(int K, int[][] grid) {
        Point start = findNextZero(grid);
        if (start == null) return false;

        // 0인 값을 발견하면 해당 위치를 기준으로 가로/세로 2칸씩 건너뛰며 K로 채움
        for (int i = start.r; i < grid.length; i += 2) {
            for (int j = start.c; j < grid[0].length; j += 2) {
                grid[i][j] = K;
            }
        }
        return true;
    }

    class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private Point findNextZero(int[][] grid) {
        int nRows = Math.min(2, grid.length);
        int nCols = Math.min(2, grid[0].length);
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < nCols; ++j) {
                if (grid[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private String toResult(int K, int[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(K).append('\n');
        for (int[] row : grid) {
            sb.append(row[0]);
            for (int j = 1; j < row.length; ++j) {
                sb.append(' ').append(row[j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int nRows, nCols;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var line = reader.readInts();
            nRows = line[0];
            nCols = line[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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
        var sol = new Solution();
        var res = sol.solution(nRows, nCols);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
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
