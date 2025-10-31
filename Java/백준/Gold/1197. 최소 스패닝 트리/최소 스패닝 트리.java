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

    public int solution(int nVertices, int nEdges, int[][] edges) {
        Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
        int[] parent = new int[nVertices + 1];
        int nSelectedEdges = 0, totalWeight = 0;

        while (nSelectedEdges < nVertices - 1) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if (find(parent, u) != find(parent, v)) {
                    union(parent, u, v);
                    nSelectedEdges++;
                    totalWeight += weight;
                }
            }
        }
        return totalWeight;
    }

    int find(int[] parent, int u) {
        if (parent[u] == 0) {
            return u;
        }
        if (parent[u] != u) {
            parent[u] = find(parent, parent[u]);
        }
        return parent[u];
    }

    void union(int[] parent, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);
        if (rootU != rootV) {
            parent[rootV] = rootU;
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final Solution solution = new Solution();
    final int nVertices, nEdges;
    final int[][] edges;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var nVE = reader.readInts();
            nVertices = nVE[0];
            nEdges = nVE[1];

            edges = new int[nEdges][3];
            for (int i = 0; i < nEdges; ++i) {
                edges[i] = reader.readInts();
            }

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
        var res = new Solution().solution(nVertices, nEdges, edges);
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
