import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.BitSet;
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
    final int N;
    final BitSet positions;

    final int[] dy = { -1, -1, 1, 1 };
    final int[] dx = { -1, 1, -1, 1 };

    Solution(int N, BitSet positions) {
        this.N = N;
        this.positions = positions;
    }

    public int solution() {
        if (N == 1)
            return positions.get(0) ? 1 : 0;
        int maxBishop = 0, i = positions.nextSetBit(0);
        while (i >= 0) {
            int res = dfs(i, 1);
            maxBishop = Math.max(maxBishop, res);
            i = positions.nextSetBit(i + 1);
        }
        return maxBishop;
    }

    int dfs(int idx, final int depth) {
        if (idx < 0 || idx >= N * N)
            return depth;

        var removed = putBishop(idx);

        int i = positions.nextSetBit(idx + 1);
        int maxDepth = depth;
        while (i >= 0) {
            var removed2 = putBishop(i);
            int res = dfs(i, depth + 1);
            resetBoard(removed2);

            maxDepth = Math.max(maxDepth, res);
            i = positions.nextSetBit(i + 1);
        }

        resetBoard(removed);

        return maxDepth;
    }

    BitSet putBishop(int pos) {
        BitSet removed = new BitSet(N * N);
        removed.set(pos);

        int row = pos / N;
        int col = pos % N;

        // 각 대각선 방향으로 탐색
        for (int dir = 0; dir < 4; dir++) {
            int r = row + dy[dir];
            int c = col + dx[dir];

            while (r >= 0 && r < N && c >= 0 && c < N) {
                int idx = r * N + c;
                if (positions.get(idx)) {
                    removed.set(idx);
                }
                r += dy[dir];
                c += dx[dir];
            }
        }

        positions.andNot(removed); // clear positions
        return removed;
    }

    void resetBoard(BitSet removed) {
        positions.or(removed);
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final BitSet[] positions;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.positions = new BitSet[] { new BitSet(N * N), new BitSet(N * N) };
            for (int y = 0; y < N; ++y) {
                String line = br.readLine();
                for (int x = 0; x < N * 2; x += 2) {
                    var idx = y * N + x / 2;
                    positions[(y + x / 2) % 2].set(idx, (line.charAt(x) == '1'));
                }
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
        int res = 0;
        for (var ps : positions) {
            res += new Solution(N, ps).solution();
        }
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}