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
    public int solution(int N) {
        // 1. int to binaryString
        String binaryString = Integer.toBinaryString(N);
        // 2. reverse string
        String reversedString = new StringBuilder(binaryString).reverse().toString();
        // 3. binaryString to int
        return Integer.parseInt(reversedString, 2);
    }
}

class Runner {
    final BufferedWriter bw;

    final int N;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;

        // var reader = new Reader(br);
        try {
            N = Integer.parseInt(br.readLine());
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
        var sol = new Solution();
        var res = sol.solution(N);
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