import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
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

    final int T;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            T = reader.readInts()[0];
            for (int i = 0; i < T; ++i) {
                reader.skipLine();
                Set<Integer> setA = reader.readIntegers().stream().collect(Collectors.toSet());
                Set<Integer> setB = reader.readIntegers().stream().collect(Collectors.toSet());

                boolean isASubsetOfB = setB.containsAll(setA);
                boolean isBSubsetOfA = setA.containsAll(setB);

                if (isASubsetOfB && isBSubsetOfA) {
                    sb.append("=\n");
                } else if (isASubsetOfB) {
                    sb.append("<\n");
                } else if (isBSubsetOfA) {
                    sb.append(">\n");
                } else {
                    sb.append("?\n");
                }
            }
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

    public void run() {
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

    public void skipLine() throws IOException {
        br.readLine();
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
        return Arrays.stream(readInts()).boxed().collect(Collectors.toList());
    }

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
