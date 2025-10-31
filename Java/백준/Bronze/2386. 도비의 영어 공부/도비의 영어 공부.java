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

    public String solution(char c, String line) {
        char upper = Character.toUpperCase(c);
        char lower = Character.toLowerCase(c);

        int count = 0;
        for (char target : line.toCharArray())
            if (target == upper || target == lower) ++count;
        return String.format("%c %d\n", c, count);
    }
}

class Runner {
    final BufferedReader br;
    final BufferedWriter bw;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;

        // var reader = new Reader(br);
        // try {
        //     N = Integer.parseInt(br.readLine());
        //     name = br.readLine();

        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
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
        while (true) {
            var line = br.readLine();
            char c = line.charAt(0);
            if ('#' == c) break;
            var res = sol.solution(c, line.substring(2));
            _write(res);
        }
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
}