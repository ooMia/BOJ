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
    public String solution(int N, int[][] grid) {

        // 숫자 2가 어떤 대각선 위에 놓였는지 확인한다.
        int k = -1;
        for (int r = 0; r < N && k == -1; ++r) {
            for (int c = 0; c < N; ++c) {
                if (grid[r][c] == 2) {
                    k = (r + c) & 1;
                    break;
                }
            }
        }

        // 서로 같은 대각선 위에 '1'이 놓여있는지 확인한다.
        for (int r = 0; r < N; ++r)
            for (int c = k ^ (r & 1); c < N; c += 2)
                if (grid[r][c] == 1)
                    return "Kiriya";

        return "Lena";
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final int[][] grid;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.grid = new int[N][];
            for (int r = 0; r < N; ++r) {
                this.grid[r] = reader.readInts();
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
        var res = new Solution().solution(N, grid);
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