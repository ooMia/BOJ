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

    public int solution(long[] L1, long[] L2) {
        // 선분 AB와 선분 CD가 주어진다
        Point A = new Point(L1[0], L1[1]), B = new Point(L1[2], L1[3]),
                C = new Point(L2[0], L2[1]), D = new Point(L2[2], L2[3]);
        int ccwA = A.ccw(C, D), ccwB = B.ccw(C, D),
                ccwC = C.ccw(A, B), ccwD = D.ccw(A, B);

        if (ccwA * ccwB < 0 && ccwC * ccwD < 0)
            return 1; // 교차
        if (ccwA == 0 && A.isOnSegment(C, D))
            return 1; // A가 B의 선분 위
        if (ccwB == 0 && B.isOnSegment(C, D))
            return 1; // B가 A의 선분 위
        if (ccwC == 0 && C.isOnSegment(A, B))
            return 1; // C가 D의 선분 위
        if (ccwD == 0 && D.isOnSegment(A, B))
            return 1; // D가 C의 선분 위

        return 0;
    }
    

    class Point {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        boolean isOnSegment(Point p0, Point p1) {
            return Math.min(p0.x, p1.x) <= this.x && this.x <= Math.max(p0.x, p1.x) &&
                    Math.min(p0.y, p1.y) <= this.y && this.y <= Math.max(p0.y, p1.y);
        }

        int ccw(Point p0, Point p1) {
            var res = (p1.x - p0.x) * (this.y - p0.y) - (p1.y - p0.y) * (this.x - p0.x);
            return Long.signum(res);
        }

        @Override
        public boolean equals(Object obj) {
            Point other = (Point) obj;
            return Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0;
        }

        @Override
        public String toString() {
            return String.format("Point [x=%s, y=%s]", x, y);
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final long[] L1;
    final long[] L2;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.L1 = reader.readLongs();
            this.L2 = reader.readLongs();
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
        var sol = new Solution();
        var res = sol.solution(L1, L2);
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

    public long[] readLongs() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        long[] longs = new long[cnt];
        for (int i = 0; i < cnt; ++i) {
            longs[i] = Long.parseLong(st.nextToken());
        }
        return longs;
    }

    public int[][] readIntArrays() throws IOException {
        return br.lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}