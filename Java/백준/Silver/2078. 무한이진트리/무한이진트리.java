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
    // (a+b, b) 또는 (a, a+b) 규칙에 따라 증식하는 이진 트리

    // a, b는 모두 자연수이기 때문에, 결과는 항상 비대칭적으로 나올 수 밖에 없다.
    // (a+b == b이기 위한 조건은 a == 0이고, a+b == a이기 위한 조건은 b == 0이다.)

    // (x, y)가 주어졌을 때, 이 값을 나오도록 한 부모 노드는 x와 y간의 대수 관계에 의해 결정적으로 정해진다.

    private int L, R;

    Solution(int L, int R) {
        this.L = L;
        this.R = R;
    }

    String solution() {
        int countL = 0, countR = 0;
        while (!(L == 1 && R == 1)) {
            if (L > R) {
                L = L - R;
                countL++;
            } else {
                R = R - L;
                countR++;
            }
        }
        return String.format("%d %d", countL, countR);
    }
}

class Runner {
    final BufferedWriter bw;

    final int L, R;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        var reader = new Reader(br);
        try {
            var line = reader.readInts();
            L = line[0];
            R = line[1];

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
        var sol = new Solution(L, R);
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