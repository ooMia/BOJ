import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    private final Map<Integer, Integer> closet;

    Solution(Map<Integer, Integer> closet) {
        this.closet = closet;
    }

    int solution() {
        int res = 1;
        for (int cnt : closet.values()) {
            res *= (cnt + 1); // 해당 종류를 입지 않는 경우 포함
        }
        return res - 1; // 아무것도 입지 않는 경우 제외
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int N;
    final Map<Integer, Integer> closet = new HashMap<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
                String[] parts = br.readLine().split(" ");
                int type = parts[1].hashCode();
                closet.put(type, closet.getOrDefault(type, 0) + 1);
            }

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
        var sol = new Solution(closet);
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