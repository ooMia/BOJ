import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
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
    final String line;

    Solution(int N, String line) {
        this.N = N;
        this.line = line;
    }

    public BigInteger solution() {
        BigInteger res = BigInteger.ZERO;
        int iStart = 0;
        while (iStart < N) {
            // find first '2'
            while (iStart < N && line.charAt(iStart) != '2')
                ++iStart;
            // find local last '2'
            int iEnd = iStart;
            while (iEnd < N && line.charAt(iEnd) == '2')
                ++iEnd;
            res = res.add(score(iStart, iEnd));
            iStart = iEnd + 1;
        }
        return res;
    }

    BigInteger score(int iStart, int iEnd) {
        // [iStart, iEnd)
        BigInteger res = BigInteger.ZERO;
        int len = iEnd - iStart;
        for (int score = 1; len > 0; ++score, --len) {
            var tmp = BigInteger.valueOf(len).multiply(BigInteger.valueOf(score));
            res = res.add(tmp);
        }
        return res;
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final String line;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = Integer.parseInt(br.readLine());
            this.line = br.readLine();
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
        var res = new Solution(N, line).solution();
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