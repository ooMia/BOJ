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

class Solution {

    final int N; // 전체 아이템 개수
    final int C; // 최대 소지 무게
    final int[] weights;

    Solution(int N, int C, int[] weights) {
        this.N = N;
        this.C = C;

        List<Integer> _weights = new ArrayList<>(N);
        for (int weight : weights) {
            if (weight <= C) { // 최대 소지 무게보다 작거나 같은 아이템만 고려
                _weights.add(weight);
            }
        }
        this.weights = _weights.stream().mapToInt(i -> i).toArray();
    }

    public int solve() {
        int n = weights.length;
        if (n <= 1) {
            return n + 1; // 공집합 포함
        }
        var sub1 = sumSubset(Arrays.copyOfRange(weights, 0, n / 2));
        var sub2 = sumSubset(Arrays.copyOfRange(weights, n / 2, n));

        Arrays.sort(sub2); // 이진 탐색을 위해 정렬

        int count = 0;
        for (var s1 : sub1) {
            if (s1 > C)
                continue;

            int l = 0, r = sub2.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (sub2[mid] + s1 > C) {
                    r = mid - 1; // 합이 최대 소지 무게를 초과
                } else {
                    l = mid + 1; // 합이 최대 소지 무게 이하
                }
            }
            count += r + 1; // sub2에서 s1과 합이 최대 소지 무게 이하인 경우의 수
        }

        return count;
    }

    long[] sumSubset(int[] weights) {
        int n = weights.length;
        List<Long> sums = new ArrayList<>(1 << n);

        for (int mask = 0; mask <= (1 << n) - 1; ++mask) {
            long sum = sum(weights, mask);
            if (sum <= C) { // 최대 소지 무게보다 작거나 같은 경우
                sums.add(sum);
            }
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

    final int N, C; // N: 아이템 개수, C: 최대 소지 무게
    final int[] weights;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nc = reader.readInts();
            N = _nc[0];
            C = _nc[1];
            weights = reader.readInts();

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
        var res = new Solution(N, C, weights).solve();
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
