import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    public String solution(KeyBoard[] keyBoards) {
        Arrays.sort(keyBoards);

        var sb = new StringBuilder();
        for (var kb : keyBoards) {
            sb.append(kb.key);
        }
        return sb.toString();
    }

}

class KeyBoard implements Comparable<KeyBoard> {
    final int id, delay;
    final char key;

    KeyBoard(int id, int delay, char key) {
        this.id = id;
        this.delay = delay;
        this.key = key;
    }

    @Override
    public int compareTo(KeyBoard o) {
        if (this.delay == o.delay) {
            return Integer.compare(this.id, o.id);
        }
        return Integer.compare(this.delay, o.delay);
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    KeyBoard[] keyBoards;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var M = reader.readInts()[1];
            this.keyBoards = new KeyBoard[M];
            for (int i = 0; i < M; ++i) {
                var input = reader.line().split(" ");
                var id = Integer.parseInt(input[0]);
                var delay = Integer.parseInt(input[1]);
                var key = input[2].charAt(0);
                keyBoards[i] = new KeyBoard(id, delay, key);
            }

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
        var sol = new Solution();
        var res = sol.solution(keyBoards);
        sb.append(res).append('\n');
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public void skipLine() throws IOException {
        br.readLine();
    }

    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    public String line() throws IOException {
        return br.readLine();
    }

    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}
