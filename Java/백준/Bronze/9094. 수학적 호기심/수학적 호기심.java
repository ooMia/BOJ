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
            int T = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < T; ++i) {
                long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                long end = System.currentTimeMillis();
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {
    int solution(int n, int m) {
        // (0, n) 구간 내 a < b를 만족하는 정수 쌍 (a, b)에 대해
        // (a^2 + b^2 + m) % (a * b) == 0 인 쌍의 개수

        int res = 0;
        for (int a = 1; a < n; ++a) {
            for (int b = a + 1; b < n; ++b) {
                if (isValid(a, b, m)) {
                    res++;
                }
            }
        }
        return res;
    }

    boolean isValid(int a, int b, int m) {
        return (a * a + b * b + m) % (a * b) == 0;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int n, m;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var line = reader.readInts();
            this.n = line[0];
            this.m = line[1];

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
        var res = sol.solution(n, m);
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