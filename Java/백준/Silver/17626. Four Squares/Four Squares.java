import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
    final int n;
    final int[] squares;

    Solution(int n) {
        this.n = n;

        int l = (int) Math.sqrt(n);
        squares = new int[l];
        for (int k = 1; k <= l; ++k)
            squares[k - 1] = k * k;
    }

    int solution() {
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.count));
        pq.offer(new Task(n, 0));

        while (!pq.isEmpty()) {
            Task curr = pq.poll();
            if (curr.n == 0)
                return curr.count;

            int iStart = Arrays.binarySearch(squares, curr.n / 3);
            iStart = iStart < 0 ? -(iStart + 1) : iStart;
            for (int i = iStart; i < squares.length; ++i) {
                int square = squares[i];
                if (square > curr.n)
                    break;
                pq.offer(new Task(curr.n - square, curr.count + 1));
            }
        }
        return -1;
    }

    class Task {
        int n, count;

        Task(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;

    final int n;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            this.n = Integer.parseInt(br.readLine());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            bw.write('\n');
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        var sol = new Solution(n);
        var res = sol.solution();
        bw.write(String.valueOf(res));
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }
}