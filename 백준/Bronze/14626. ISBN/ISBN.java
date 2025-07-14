import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        try {
            IRunner runner = new Runner(br, bw);
            runner.run();
            runner.flush();

            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final char[] isbn;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            isbn = reader.lines().get(0).toCharArray();
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
    public void run() {
        int missingIndex = -1;
        int sumWithoutMissing = 0;
        for (int i = 0; i < isbn.length; i++) {
            if (isbn[i] == '*') {
                missingIndex = i;
                continue;
            }
            sumWithoutMissing += calcWeight(isbn[i], i);
        }
        for (int n = (10 - (sumWithoutMissing % 10)) % 10; n <= 27; n += 10) {
            if (missingIndex % 2 == 0 && n < 10) {
                sb.append(n).append('\n');
                break;
            }
            if (missingIndex % 2 == 1 && n % 3 == 0) {
                sb.append(n / 3).append('\n');
                break;
            }
        }
    }

    private int calcWeight(char c, int idx) {
        return (c - '0') * (idx % 2 == 0 ? 1 : 3);
    }
}

interface IRunner {
    void run();

    void flush();
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
        return Arrays.stream(readInts()).boxed().collect(Collectors.toList());
    }

    @Override
    public List<String> lines() throws IOException {
        return br.lines().collect(Collectors.toList());
    }
}

interface IReader {
    void skipLine() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;

    List<String> lines() throws IOException;
}
