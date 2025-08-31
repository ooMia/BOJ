import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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

    final int N, K; // N: 수빈이의 위치, K: 동생의 위치

    Solution(int N, int K) {
        this.N = N;
        this.K = K;
    }

    public long solve() {
        PriorityQueue<History> pq = new PriorityQueue<>();
        Map<Integer, Integer> visited = new HashMap<>();
        pq.offer(new History(0, N));
        visited.put(N, 0);

        while (!pq.isEmpty()) {
            History current = pq.poll();
            int t = current.time;
            int p = current.pos;

            if (p == K) {
                return t;
            }

            // 다음 위치를 큐에 추가
            if (p - 1 >= 0) {
                var key = p - 1;
                if (!visited.containsKey(key) || visited.get(key) > t + 1) {
                    pq.offer(new History(t + 1, key));
                    visited.put(key, t + 1);
                }
            }

            if (p + 1 <= 100000) {
                var key = p + 1;
                if (!visited.containsKey(key) || visited.get(key) > t + 1) {
                    pq.offer(new History(t + 1, key));
                    visited.put(key, t + 1);
                }
            }
            if (p * 2 <= 100000) {
                var key = p * 2;
                if (!visited.containsKey(key) || visited.get(key) > t) {
                    pq.offer(new History(t, key));
                    visited.put(key, t);
                }
            }
        }
        return -1;
    }

    class History implements Comparable<History> {
        final int time, pos;

        History(int time, int pos) {
            this.time = time;
            this.pos = pos;
        }

        @Override
        public int compareTo(History o) {
            return Integer.compare(this.time, o.time);
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, K; // N: 수빈이의 위치, K: 동생의 위치

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nk = reader.readInts();
            this.N = _nk[0];
            this.K = _nk[1];

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
        var res = new Solution(N, K).solve();
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
