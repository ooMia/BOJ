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

    final int size;
    final int[][] grid;

    Solution(int size, int[][] grid) {
        this.size = size;
        this.grid = grid;
    }

    int solution() {
        if (size == 1)
            return grid[0][0];

        return solve(0, 0, size);
    }

    int solve(int r, int c, int size) {
        if (size == 1) return grid[r][c];
        if (size == 2) {
            int[] values = {grid[r][c], grid[r][c + 1], grid[r + 1][c], grid[r + 1][c + 1]};
            Arrays.sort(values);
            return values[1];
        }

        int midR = r + size / 2, midC = c + size / 2;
        int[] values = { solve(r, c, size / 2), solve(r, midC, size / 2), solve(midR, c, size / 2), solve(midR, midC, size / 2) };
        Arrays.sort(values);
        return values[1];
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int size;
    final int[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            size = Integer.parseInt(br.readLine());
            grid = new int[size][];
            for (int r = 0; r < size; ++r)
                grid[r] = reader.readInts();

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
        var sol = new Solution(size, grid);
        var res = sol.solution();
        bw.write(String.valueOf(res));
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