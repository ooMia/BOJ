import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
        final int maxNodeId;
        final int[] head;   // 크기: maxNodeId + 2
        final int[] edges;  // 크기: m (유효 간선 수)

        Graph(int destId, int[][] paths) {
            this.maxNodeId = destId;

            // 차수 계산
            int[] deg = new int[maxNodeId + 1];
            for (int[] e : paths) {
                if (e == null || e.length < 2) continue;
                int x = e[0], y = e[1];
                if (1 <= x && x < y && y <= maxNodeId) deg[x]++;
            }

            // prefix sum으로 head 구성
            this.head = new int[maxNodeId + 2];
            for (int i = 1; i <= maxNodeId; i++) head[i + 1] = head[i] + deg[i];

            // edges 채우기
            this.edges = new int[head[maxNodeId + 1]];
            int[] cur = Arrays.copyOf(head, head.length);
            for (int[] e : paths) {
                if (e == null || e.length < 2) continue;
                int x = e[0], y = e[1];
                if (1 <= x && x < y && y <= maxNodeId) {
                    edges[cur[x]++] = y;
                }
            }
        }
    }

    // 1에서 destId까지의 최소 변화 횟수. 불가능하면 -1
    public int solution(int destId, Graph g) {
        if (destId == 1) return 0;
        if (g == null || g.maxNodeId < 1) return -1;
        if (destId > g.maxNodeId) return -1;

        int n = g.maxNodeId;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // 고정 배열 큐(원형 큐)
        int[] q = new int[Math.max(2, n + 1)];
        int h = 0, t = 0;

        dist[1] = 0;
        q[t++] = 1;

        while (h < t) {
            int u = q[h++];
            // 도착 체크
            if (u == destId) return dist[u];

            // CSR 범위: [head[u], head[u+1])
            int begin = g.head[u];
            int end = g.head[u + 1];
            for (int i = begin; i < end; i++) {
                int v = g.edges[i];
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    if (v == destId) return dist[v];
                    q[t++] = v;
                }
            }
        }
        return -1;
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
