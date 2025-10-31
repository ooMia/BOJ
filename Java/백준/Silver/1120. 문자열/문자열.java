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
    final String from, to;

    Solution(String from, String to) {
        this.from = from;
        this.to = to;
    }

    int solution() {
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i + from.length() <= to.length(); ++i) {
            minDiff = Math.min(minDiff, difference(i));
        }
        return minDiff;
    }

    int difference(int iStart) {
        // iStart + from.length() <= to.length()를 만족하는 iStart에 대해
        // to.subStr(iStart, to.length())과 from의 차이를 구한다.
        int diff = 0;
        for (int i = 0; i < from.length(); ++i)
            if (from.charAt(i) != to.charAt(iStart + i))
                ++diff;
        return diff;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final String A, B;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var ab = br.readLine().split(" ");
            A = ab[0];
            B = ab[1];

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
        var sol = new Solution(A,B);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}