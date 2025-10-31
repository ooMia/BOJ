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

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M; // N: number of items, M: number to choose from
    final int[] numbers;
    final int[] result; // to store the current permutation
    final boolean[] visited; // to track used numbers

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            result = new int[M];
            visited = new boolean[N];
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
        Itertools.product(numbers, M)
                .forEach(l -> {
                    for (int val : l) {
                        sb.append(val).append(' ');
                    }
                    sb.append('\n');
                });
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

class Itertools {
    public static List<int[]> product(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        if (r < 0) {
            return resultList;
        }
        if (r == 0) {
            resultList.add(new int[0]);
            return resultList;
        }

        int n = numbers.length;
        if (n == 0 && r > 0) {
            return resultList;
        }

        List<int[]> tempResult = new ArrayList<>();
        tempResult.add(new int[0]);

        for (int i = 0; i < r; i++) {
            List<int[]> nextResult = new ArrayList<>();
            for (int[] prev : tempResult) {
                for (int num : numbers) {
                    int[] newProduct = new int[prev.length + 1];
                    System.arraycopy(prev, 0, newProduct, 0, prev.length);
                    newProduct[prev.length] = num;
                    nextResult.add(newProduct);
                }
            }
            tempResult = nextResult;
        }

        resultList = tempResult;
        return resultList;
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
