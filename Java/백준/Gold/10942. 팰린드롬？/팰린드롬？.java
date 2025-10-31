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
    final int N;
    final int[] numbers;

    final int[][] dp; // -1: unknown, 1: possible, 0: impossible

    public Solution(int N, int[] numbers) {
        this.N = N;
        this.numbers = numbers;
        this.dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
    }

    public int solution(int iFrom, int iTo) {
        if (iFrom == iTo)
            return 1;
        if (dp[iFrom][iTo] >= 0)
            return dp[iFrom][iTo];

        var res = isPalindrome(iFrom, iTo) ? 1 : 0;

        // 결과 전파
        if (res == 1) {
            // 내부도 팰린드롬
            while (iFrom < iTo) {
                if (dp[iFrom][iTo] >= 0)
                    break;
                else
                    dp[iFrom++][iTo--] = res;
            }
        } else {
            // 외부도 팰린드롬 아님
            while (0 <= iFrom && iTo < N) {
                if (dp[iFrom][iTo] >= 0)
                    break;
                else
                    dp[iFrom--][iTo++] = res;
            }
        }
        return res;
    }

    boolean isPalindrome(int iFrom, int iTo) {
        while (iFrom < iTo) {
            if (dp[iFrom][iTo] >= 0)
                return dp[iFrom][iTo] == 1;
            if (numbers[iFrom] != numbers[iTo])
                return false;
            iFrom++;
            iTo--;
        }
        return true;
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;
    final int M;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            numbers = reader.readInts();
            this.M = reader.readInts()[0];

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
        var sol = new Solution(N, numbers);
        for (int m = 0; m < M; m++) {
            var input = reader.readInts();
            var res = sol.solution(input[0] - 1, input[1] - 1);
            sb.append(res).append('\n');
        }
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
