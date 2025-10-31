import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    final int N; // 정수의 개수
    final int S; // 목표 합
    final int[] numbers;

    Solution(int N, int S, int[] numbers) {
        this.N = N;
        this.S = S;
        this.numbers = numbers;
    }

    public long solve() {
        int n = numbers.length;
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return numbers[0] == S ? 1 : 0;
        }

        var sub1 = sumSubset(Arrays.copyOfRange(numbers, 0, n / 2));
        var _sub2 = sumSubset(Arrays.copyOfRange(numbers, n / 2, n));

        Map<Long, Integer> sub2 = new HashMap<>();
        for (long s2 : _sub2) {
            sub2.put(s2, sub2.getOrDefault(s2, 0) + 1);
        }

        long count = 0;
        for (var s1 : sub1) {
            count += sub2.getOrDefault(S - s1, 0);
        }

        if (S == 0) {
            count -= 1;
        }

        return count;
    }

    long[] sumSubset(int[] weights) {
        int n = weights.length;
        List<Long> sums = new ArrayList<>(1 << n);

        for (int mask = 0; mask <= (1 << n) - 1; ++mask) {
            long sum = sum(weights, mask);
            sums.add(sum);
        }
        return sums.stream().mapToLong(i -> i).toArray();
    }

    long sum(int[] arr, int mask) {
        long sum = 0;
        for (int i = 0; i < arr.length; ++i) {
            if ((mask & (1 << i)) != 0) {
                sum += arr[i];
            }
        }
        return sum;
    }

}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, S; // N: 정수의 개수, S: 목표 합
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ns = reader.readInts();
            N = _ns[0];
            S = _ns[1];
            numbers = reader.readInts();

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
        var res = new Solution(N, S, numbers).solve();
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
