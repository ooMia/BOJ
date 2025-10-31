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
    final int N;
    final int[][] points;

    Solution(int N, int[][] points) {
        this.N = N;
        this.points = points;
    }

    public int solution() {
        // N개의 인덱스 중 3개를 선택하는 모든 조합에 대해 직교 여부 확인
        int count = 0;
        for (int i = 0; i < N - 2; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < N - 1; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                for (int k = j + 1; k < N; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    if (hasOrthogonal(x1, y1, x2, y2, x3, y3)) ++count;
                }
            }
        }
        return count;
    }

    boolean hasOrthogonal(int _x1, int y1, int x2, int y2, int x3, int y3) {
        // 문제 조건: 세 점은 반드시 서로 다름
        // 같을 수 있다면 경우에 따라 false 반환

        long x1 = _x1; // 1,000,000,000 ^ 2은 10^18로 int 범위 초과

        // 일직선 판별 (외적 == 0)
        long cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (cross == 0) return false;

        // 직교 판별 (내적 == 0)
        long Ax = x2 - x1, Ay = y2 - y1; // A = P1-P2
        long Bx = x3 - x1, By = y3 - y1; // B = P1-P3
        long Cx = x3 - x2, Cy = y3 - y2; // C = P2-P3

        if (Ax * Bx + Ay * By == 0) return true; // A . B
        if (Ax * Cx + Ay * Cy == 0) return true; // A . C
        if (Bx * Cx + By * Cy == 0) return true; // B . C
        return false;
    }
}

class Runner {
    final BufferedWriter bw;

    final int N;
    final int[][] points;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            N = Integer.parseInt(br.readLine());
            points = new int[N][];
            for (int i = 0; i < N; ++i) {
                points[i] = reader.readInts();
            }

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
        var sol = new Solution(N, points);
        var res = sol.solution();
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