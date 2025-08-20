import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;
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

    final int N, M;
    final int[][] priorities;

    Map<Integer, Node> nodes;

    Solution(int N, int M, int[][] priorities) {
        this.N = N;
        this.M = M;
        this.priorities = priorities;

        this.nodes = new TreeMap<>();
    }

    public String solution() {
        for (var p : priorities) {
            int a = p[0], b = p[1];
            if (!nodes.containsKey(a))
                nodes.put(a, new Node(a));
            nodes.get(a).addChild(b);
        }

        var sb = new StringBuilder();
        for (int i = 1; i <= N; ++i) {
            if (!nodes.containsKey(i)) {
                sb.append(i).append(' ');
            }
        }
        Queue<Integer> pq = new LinkedList<>();
        for (var entry : nodes.entrySet()) {
            if (entry.getValue().inDegree == 0)
                pq.add(entry.getKey());
        }
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(' ');
            for (int childId : nodes.get(cur).children) {
                Node child = nodes.get(childId);
                child.inDegree--;
                if (child.inDegree == 0)
                    pq.add(childId);
            }
        }
        return sb.toString();
    }

    class Node {
        final int id;
        final List<Integer> children;
        int inDegree;

        Node(int id) {
            this.id = id;
            this.children = new ArrayList<>();
            this.inDegree = 0;
        }

        void addChild(int childId) {
            children.add(childId);
            if (!nodes.containsKey(childId)) {
                nodes.put(childId, new Node(childId));
            }
            nodes.get(childId).inDegree++;
        }
    }
}

class Runner {
    final Reader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M;
    final int[][] priorities;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;
        try {
            var _nm = reader.readInts();
            this.N = _nm[0];
            this.M = _nm[1];
            this.priorities = new int[M][];
            for (int i = 0; i < M; ++i) {
                priorities[i] = reader.readInts();
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
        var sol = new Solution(N, M, priorities);
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
}