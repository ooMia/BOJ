import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    int N, M; // N: number of elements, M: length of sub-sequences
    int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nm = reader.readInts();
            N = nm[0];
            M = nm[1];
            numbers = reader.readInts();
            Arrays.sort(numbers);
            sb.ensureCapacity(M * N * N);
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
        Set<String> history = new HashSet<>();
        Itertools.combinations(numbers, M).forEach(chosen -> {
            String key = Arrays.toString(chosen);
            if (!history.contains(key)) {
                history.add(key);
                for (int i = 0; i < M; i++) {
                    sb.append(chosen[i]).append(' ');
                }
                sb.append('\n');
            }
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
    public static List<int[]> combinations(int[] numbers, int r) {
        List<int[]> resultList = new ArrayList<>();
        int n = numbers.length;

        if (r > n || r < 0) {
            return resultList;
        }

        int[] indices = new int[r];
        for (int i = 0; i < r; i++) {
            indices[i] = i;
        }

        while (true) {
            // 현재 조합 추가
            int[] currentCombination = new int[r];
            for (int i = 0; i < r; i++) {
                currentCombination[i] = numbers[indices[i]];
            }
            resultList.add(currentCombination);

            // 다음 조합으로 이동
            int i;
            for (i = r - 1; i >= 0 && indices[i] == n - r + i; i--)
                ;
            if (i < 0) {
                break; // 모든 조합을 생성했으면 종료
            }
            indices[i]++;
            for (int j = i + 1; j < r; j++) {
                indices[j] = indices[j - 1] + 1;
            }
        }

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
