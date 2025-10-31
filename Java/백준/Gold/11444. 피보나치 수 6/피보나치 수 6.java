import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            Runner runner = new Runner(br, bw);
            runner.run();
            runner.flush();
            
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    final int n;
    final long mod = 1_000_000_007;
    final Map<Integer, Long> fibo = new HashMap<>();

    Solution(long _n) {
        final long cycle = 2_000_000_016; // pre-calculated cycle length for given mod
        this.n = (int) (_n % cycle);
        this.fibo.put(0, 0L);
        this.fibo.put(1, 1L);
        this.fibo.put(2, 1L);
        this.fibo.put(3, 2L);
    }

    public long solution() {
        if (n <= 1)
            return n;
        return fibo(n);
    }

    long fibo(int k) {
        if (fibo.containsKey(k)) {
            return fibo.get(k);
        }
        // use d'Ocagne's identity
        // F(m+n) = F(m-1)*F(n) + F(m)*F(n+1)
        int n = k / 2, m = k - n;
        long P1 = (fibo(m - 1) * fibo(n)) % mod;
        long P2 = (fibo(m) * fibo(n + 1)) % mod;
        long res = (P1 + P2) % mod;
        fibo.put(k, res);
        return res;
    }

}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final long n;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.n = Long.parseLong(reader.line());

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution(n);
        var res = sol.solution();
        sb.append(res).append('\n');
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public String line() throws IOException {
        return br.readLine();
    }
}
