import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
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
    final Set<Integer>[] edges;
    final BitSet visited;
    final int[] counts;

    Solution(int nNodes, Set<Integer>[] edges, int rootId) {
        this.edges = edges;
        this.visited = new BitSet(nNodes + 1);
        this.counts = new int[nNodes + 1];
        count(rootId);
    }

    private void count(int rootId) {
        var children = edges[rootId];
        counts[rootId] = 1;
        visited.set(rootId);
        for (var child : children) {
            if (visited.get(child)) continue;
            count(child);
            counts[rootId] += counts[child];
        }
    }

    public int solution(int queryId) {
        return counts[queryId];
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int nNodes, rootId, nQueries;
    final Set<Integer>[] edges;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var input = reader.readInts();
            this.nNodes = input[0];
            this.rootId = input[1];
            this.nQueries = input[2];

            this.edges = new HashSet[nNodes + 1];
            for (int i = 0; i < nNodes + 1; ++i) {
                edges[i] = new HashSet<>();
            }

            for (int i = 1; i < nNodes; ++i) {
                var edge = reader.readInts();
                edges[edge[0]].add(edge[1]);
                edges[edge[1]].add(edge[0]);
            }

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
        var sol = new Solution(nNodes, edges, rootId);
        for (int i = 0; i < nQueries; ++i) {
            var res = sol.solution(Integer.parseInt(reader.line()));
            sb.append(res).append('\n');
        }
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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }

    public List<Integer> readIntegers() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int cnt = st.countTokens();
        List<Integer> res = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; ++i) {
            res.add(Integer.parseInt(st.nextToken()));
        }
        return res;
    }

    public int[][] readIntArrays() throws IOException {
        return lines().map(s -> Arrays.stream(s.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
                .toArray(int[][]::new);
    }
}