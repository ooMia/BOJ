import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
    final int id, o;
    int w, status = 1;

    /**
     * @param id 시나리오 번호
     * @param o  적정 체중
     * @param w  현재 체중
     */
    Solution(int id, int o, int w) {
        this.id = id;
        this.o = o;
        this.w = w;
    }

    void solution(char behavior, int value) {
        if (status < 0) // Dead
            return;
        switch (behavior) {
            case 'F': // Increase weight
                w += value;
                break;
            case 'E': // Decrease weight
                w -= value;
                break;
        }

        if (w <= 0) // Dead
            status = -1;
        else if (o / 2 < w && w < o * 2) // Success
            status = 1;
        else // Fail
            status = 0;
    }

    String status() {
        return switch (status) {
            case -1 -> "RIP";
            case 0 -> ":-(";
            default -> ":-)";
        };
    }

    @Override
    public String toString() {
        return String.format("%d %s", id, status());
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final Stream<String> inputs;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            inputs = br.lines();
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
        final String endOfScenario = "# 0", endOfCase = "0 0";
        var iter = inputs.iterator();

        int id = 1;
        while (true) {
            var line1 = iter.next();
            if (endOfCase.equals(line1))
                break;

            var s1 = line1.split(" ");
            var sol = new Solution(id++, Integer.parseInt(s1[0]), Integer.parseInt(s1[1]));
            while (true) {
                var line2 = iter.next();
                if (endOfScenario.equals(line2))
                    break;
                var s2 = line2.split(" ");
                sol.solution(s2[0].charAt(0), Integer.parseInt(s2[1]));
            }
            bw.write(sol.toString() + "\n");
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}