import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            int T = 1;
            for (int i = 0; i < T; ++i) {
                IRunner runner = new Runner(br, bw);
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

    final Map<String, Integer> likes;

    Solution(int N, String[] names, String[][] likes) {
        this.likes = new java.util.HashMap<>(N);
        for (int i = 0; i < N; ++i)
            this.likes.put(names[i], 0);
        for (int i = 0; i < N; ++i)
            for (String like : likes[i])
                this.likes.put(like, this.likes.getOrDefault(like, 0) + 1);
    }

    public String solution() {
        var l = likes.entrySet().stream()
                .sorted((o1, o2) -> {
                    int cmp = Integer.compare(o2.getValue(), o1.getValue());
                    return cmp != 0 ? cmp : o1.getKey().compareTo(o2.getKey());
                });
        StringBuilder sb = new StringBuilder();
        l.forEach(e -> sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n'));
        return sb.toString();
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final String[] names;
    final String[][] likes;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            this.names = reader.line().split(" ");
            this.likes = new String[N][];
            for (int i = 0; i < N; ++i) {
                likes[i] = reader.line().split(" ");
            }

            sb.ensureCapacity(20);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        try {
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() throws IOException {
        var res = new Solution(N, names, likes).solution();
        sb.append(res).append('\n');
    }
}

class Reader implements IReader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void skipLine() throws IOException {
        br.readLine();
    }

    @Override
    public Stream<String> lines() throws IOException {
        return br.lines();
    }

    @Override
    public String line() throws IOException {
        return br.readLine();
    }

    @Override
    public int[] readInts() throws IOException {
        String[] tokens = br.readLine().split(" ");
        int[] ints = new int[tokens.length];
        for (int i = 0; i < tokens.length; ++i) {
            ints[i] = Integer.parseInt(tokens[i]);
        }
        return ints;
    }

    @Override
    public List<Integer> readIntegers() throws IOException {
        String[] tokens = br.readLine().split(" ");
        var res = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            res.add(Integer.valueOf(token));
        }
        return res;
    }
}

interface IRunner {
    void run() throws IOException;

    void flush();
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
