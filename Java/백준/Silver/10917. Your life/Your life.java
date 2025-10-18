import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            // int T = Integer.parseInt(br.readLine().trim());
            // for (int i = 0; i < T; ++i)
            {
                // System.err.println("\ncase " + (i + 1));
                // long start = System.currentTimeMillis();
                Runner runner = new Runner(br, bw);
                runner.run();
                runner.flush();
                // long end = System.currentTimeMillis();
                // System.err.println("took " + (end - start) + " ms");
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    static class Graph {

        private final int maxNodeId;
        private final int[][] adj;

        Graph(int destId, int[][] paths) {
            // (x, y) = paths[0], paths[1]
            // 1 <= x < y <= N
            this.maxNodeId = destId;
            this.adj = paths;
            Arrays.sort(adj, (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });
        }

        List<Integer> getNeighbors(int nodeId) {
            // find neighbors of nodeId
            // 1. find minimal index using binary search
            // 2. collect neighbors until nodeId changes

            int left = 0, right = adj.length - 1;
            while (left <= right) {
                int adder = right - left >= 2 ? (right - left) >>> 1 : 0;
                int mid = left + adder;
                if (adj[mid][0] < nodeId) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (left >= adj.length || adj[left][0] != nodeId) {
                return List.of();
            }

            List<Integer> neighbors = new ArrayList<>();
            for (int i = left; i < adj.length && adj[i][0] == nodeId; ++i) {
                neighbors.add(adj[i][1]);
            }
            return neighbors;
        }
    }

    public int solution(int destId, Graph graph) {
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.depth, b.depth));
        pq.offer(new State(-1, -1, 1));

        boolean[] visited = new boolean[graph.maxNodeId + 1];
        while (!pq.isEmpty()) {
            State curr = pq.poll();
            if (curr.to == destId) return curr.depth + 1;

            for (int neighbor : graph.getNeighbors(curr.to)) {
                if (!visited[neighbor]) {
                    pq.offer(new State(curr.depth + 1, curr.to, neighbor));
                    visited[neighbor] = true;
                }
            }
        }

        return -1;
    }

    class State {
        int depth, from, to;

        public State(int depth, int from, int to) {
            this.depth = depth;
            this.from = from;
            this.to = to;
        }
    }
}

class Runner {
    final BufferedWriter bw;

    final int nInputs;
    final int destId;
    final Solution.Graph graph;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.bw = bw;
        var reader = new Reader(br);
        try {
            int[] line = reader.readInts();
            this.destId = line[0];
            this.nInputs = line[1];

            if (nInputs > 0) {
                int[][] paths = new int[nInputs][];
                for (int i = 0; i < nInputs; ++i) {
                    paths[i] = reader.readInts();
                }
                graph = new Solution.Graph(destId, paths);
            } else {
                graph = null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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
        var sol = new Solution();
        if (nInputs == 0) {
            _write(-1);
            return;
        }
        var res = sol.solution(destId, graph);
        _write(res);
    }

    private void _write(Object o) {
        try {
            bw.write(String.valueOf(o));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Reader {
    private BufferedReader br;

    public Reader(BufferedReader br) {
        this.br = br;
    }

    public int readInt() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    public int[] readInts() throws IOException {
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line, " ");
        int cnt = st.countTokens();
        int[] ints = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            ints[i] = Integer.parseInt(st.nextToken());
        }
        return ints;
    }
}

