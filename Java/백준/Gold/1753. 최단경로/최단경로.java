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

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int V, E; // V: number of vertices (1...V), E: number of edges
    final int K; // K: starting vertex
    final Graph graph; // from -> (to -> weight)

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _ve = reader.readInts();
            V = _ve[0];
            E = _ve[1];
            K = reader.readInts()[0];
            graph = new Graph(V, E);
            for (int i = 0; i < E; ++i) {
                var line = reader.readInts();
                graph.addEdge(line[0], line[1], line[2]);
            }
            sb.ensureCapacity(V * 2);
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
        for (int v = 1; v <= V; ++v) {
            Integer route = graph.route(K, v);
            if (route == null) {
                sb.append("INF\n");
            } else {
                sb.append(route).append('\n');
            }
        }
    }
}

class Graph implements IGraph {
    private final static int INF = Integer.MAX_VALUE;

    final int V, E; // V: number of vertices (1...V), E: number of edges
    final Map<Integer, Map<Integer, Integer>> graph; // from -> (to -> weight)
    final Map<Integer, int[]> dist; // from -> (to -> weight)

    Graph(int V, int E) {
        this.V = V;
        this.E = E;
        this.graph = new HashMap<>(E);
        this.dist = new HashMap<>();
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        graph.computeIfAbsent(from, k -> new HashMap<>())
                .merge(to, weight, Math::min);
    }

    @Override
    public Integer route(int from, int to) {
        if (dist.get(from) == null) {
            dist.put(from, dijkstra(from));
        }
        var res = dist.get(from)[to];
        return res == INF ? null : res;
    }

    private int[] dijkstra(int from) {
        int[] dist = new int[V + 1];
        for (int i = 1; i <= V; ++i)
            dist[i] = INF;
        dist[from] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] { from, 0 });

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u])
                continue;
            if (!graph.containsKey(u))
                continue;
            for (var entry : graph.get(u).entrySet()) {
                int v = entry.getKey(), w = entry.getValue();
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new int[] { v, dist[v] });
                }
            }
        }
        return dist;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    public Integer getWeight(int from, int to) {
        return graph.getOrDefault(from, Map.of()).getOrDefault(to, null);
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

interface IGraph {
    void addEdge(int from, int to, int weight); // add edge with weight, if exists, keep the minimum weight

    Integer route(int from, int to);
}

interface IReader {
    void skipLine() throws IOException;

    Stream<String> lines() throws IOException;

    String line() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;
}
