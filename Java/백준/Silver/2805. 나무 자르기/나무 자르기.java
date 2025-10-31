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
    final int N, M;
    final int[] woods;
    final long[] cumuls;

    Solution(int N, int M, int[] woods) {
        this.N = N;
        this.M = M;
        this.woods = woods;
        Arrays.sort(woods);
        this.cumuls = new long[N + 1];// sum [0, i)
        for (int i = 1; i <= N; ++i) {
            cumuls[i] = cumuls[i - 1] + woods[i - 1];
        }
    }

    public long solution() {
        int low = 0, high = woods[N - 1] - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (cut(mid) >= M) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    long cut(int height) {
        // 1. 오름차순으로 정렬된 woods에 대해
        // 2. height보다 크거나 같은 나무의 최소 인덱스 iWood를 찾고
        int iWood = Arrays.binarySearch(woods, height);
        if (iWood < 0) {
            iWood = -iWood - 1;
        }

        // 3. 그 나무를 기준으로 C - B - A를 계산한다.
        // C: 전체 나무의 높이 합 = cumuls[N]
        // B: height보다 크거나 같은 나무들이 cut 이후 남게되는 높이의 합 = (N - iWood) * height
        // A: height보다 작은 나무들의 높이 합 = cumuls[iWood]
        var C = cumuls[N];
        var B = (N - iWood) * (long) height;
        var A = cumuls[iWood];
        return C - B - A;
    }

}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[] woods;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nm = reader.readInts();
            this.N = _nm[0];
            this.M = _nm[1];
            this.woods = reader.readInts();

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
        var res = new Solution(N, M, woods).solution();
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
