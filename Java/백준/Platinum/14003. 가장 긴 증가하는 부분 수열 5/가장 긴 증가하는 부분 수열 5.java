import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
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
    final int N;
    final int[] numbers;

    public Solution(int N, int[] number) {
        this.N = N;
        this.numbers = number;
    }

    public String solution() {
        List<Integer> res = new ArrayList<>();
        List<Integer> index = new ArrayList<>();

        int[] prev = new int[N];
        Arrays.fill(prev, -1);

        for (int i = 0; i < N; ++i) {
            var n = numbers[i];
            int pos = findPos(res, n);
            if (pos == res.size()) {
                res.add(n);
                index.add(i);
            } else {
                res.set(pos, n);
                index.set(pos, i);
            }
            if (pos > 0) {
                prev[i] = index.get(pos - 1);
            }
        }

        var sb = new StringBuilder();
        sb.append(res.size()).append("\n");
        var stack = new Stack<Integer>();
        int idx = index.get(index.size() - 1);
        while (idx != -1) {
            stack.push(numbers[idx]);
            idx = prev[idx];
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    int findPos(List<Integer> list, int target) {
        // idx는 동일한 요소가 없는 경우, 자신보다 큰 요소가 처음으로 나오는 위치
        var idx = Collections.binarySearch(list, target);
        return idx < 0 ? -(idx + 1) : idx;
    }
}

class Runner  {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final int[] numbers;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.N = reader.readInts()[0];
            this.numbers = reader.readInts();

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
        var sol = new Solution(N, numbers);
        var res = sol.solution();
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
