import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        // 1. 점 A이 원점에 대응되도록 모든 점 이동
        long x0 = L1[0], y0 = L1[1];
        var ps = new Point[] {
                new Point(0, 0),
                new Point(L1[2], L1[3]).translate(-x0, -y0),
                new Point(L2[0], L2[1]).translate(-x0, -y0),
                new Point(L2[2], L2[3]).translate(-x0, -y0)
        };

        // 동일한 선분으로 주어지지 않은 두 점이 서로 동일한 경우
        if (ps[0].equals(ps[2]) || ps[0].equals(ps[3]) || ps[1].equals(ps[2]) || ps[1].equals(ps[3]))
            return 1;

        // 2. 점 B를 x축 양의 방향에 대응
        // 만약 점 B가 이미 x축 양의 방향 위에 있다면 스킵
        // tan(theta) = B.y / B.x
        double Bx = ps[1].x, By = ps[1].y;
        ps[1].rotate(Bx, By, true);
        ps[2].rotate(Bx, By, true);
        ps[3].rotate(Bx, By, true);

        // 3. 직선 CD와 x축의 교점 targetX 구하기
        Point B = ps[1], C = ps[2], D = ps[3];
        // 3-1. x축과 평행한 경우 (C.y == D.y)
        if (C.y == D.y) {
            if (C.y != 0)
                return 0;
            // 네 점이 모두 x축 위에 존재하는 경우
            // 네 점의 x좌표를 id와 함께 할당 후 정렬
            // 첫 두 점의 id가 다르면 교차
            List<double[]> points = new ArrayList<>();
            points.add(new double[] { 0, 0 });
            points.add(new double[] { B.x, 0 });
            points.add(new double[] { C.x, 1 });
            points.add(new double[] { D.x, 1 });
            Collections.sort(points, (a, b) -> Double.compare(a[0], b[0]));
            return points.get(0)[1] != points.get(1)[1] ? 1 : 0;
        }
        // 3-2. y축과 평행한 경우 (C.x == D.x)
        else if (C.x == D.x) {
            if (C.x < 0 || B.x < C.x)
                return 0;
            else if (Math.min(C.y, D.y) <= 0 && 0 <= Math.max(C.y, D.y))
                return 1;
            else
                return 0;
        }
        // 3-3. 그 외의 경우, 직선 CD와 y=0의 교점
        else {
            double m = (D.y - C.y) / (D.x - C.x);
            double targetX = C.x - C.y / m;
            // 4. 직선 CD의 교점의 x 좌표가 선분 AB 위에 있는지
            // 있다면 선분 CD 각 점의 y 좌표가 x축과 닿을 수 있는지
            if (0 <= targetX && targetX <= B.x && Math.min(C.y, D.y) <= 0 && 0 <= Math.max(C.y, D.y))
                return 1;
        }

        return 0;
    }

    class Point {
        double x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        Point translate(long dx, long dy) {
            this.x += dx;
            this.y += dy;
            return this;
        }

        Point rotate(double tanX, double tanY, boolean reverse) {
            if (this.x == 0 && this.y == 0)
                return this;

            double diag = Math.hypot(tanX, tanY);
            if (diag == 0)
                return this;

            double cos = tanX / diag;
            double sin = tanY / diag * (reverse ? -1 : 1);
            double newX = this.x * cos - this.y * sin;
            double newY = this.x * sin + this.y * cos;

            this.x = newX;
            this.y = newY;
            return this;
        }

        double ccw(Point p0, Point p1) {
            return (p1.x - p0.x) * (this.y - p0.y) - (p1.y - p0.y) * (this.x - p0.x);
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