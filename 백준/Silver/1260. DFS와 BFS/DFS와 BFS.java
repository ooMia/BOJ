import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        try {
            IRunner runner = new Runner(br, bw);
            runner.run();
            runner.flush();

            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Util {
    static List<Integer> range(int first, int last) {
        List<Integer> result = new ArrayList<>();
        for (int i = first; i <= last; ++i) {
            result.add(i);
        }
        return result;
    }
}

class AbilityBoard implements IAbilityBoard {
    final int N;
    final int[][] S;

    public AbilityBoard(final int N, final int[][] s) {
        this.N = N;
        this.S = s;
    }

    @Override
    public int absScoreDiff(boolean[] teamCase) {
        int team1Score = 0, team2Score = 0;

        for (int i = 0; i < N; ++i) {
            var myTeam = teamCase[i];

            for (int j = i + 1; j < N; ++j) {
                if (i == j)
                    continue; // 자기 자신과의 능력치는 제외
                if (myTeam == teamCase[j]) {
                    // 같은 팀이면 능력치 합산
                    if (myTeam) {
                        team1Score += S[i][j] + S[j][i];
                    } else {
                        team2Score += S[i][j] + S[j][i];
                    }
                }
            }
        }
        return Math.abs(team1Score - team2Score);
    }

}

class Itertools<T> {

    public List<Set<T>> combinations(List<T> iterable, int r) {
        List<Set<T>> result = new ArrayList<>();
        generateCombinations(iterable, r, 0, new LinkedHashSet<>(), result);
        return result;
    }

    private void generateCombinations(List<T> iterable, int r, int start, Set<T> current, List<Set<T>> result) {
        // 원하는 크기에 도달하면 결과에 추가
        if (current.size() == r) {
            result.add(new LinkedHashSet<>(current));
            return;
        }

        // 남은 요소로 조합을 완성할 수 없으면 종료
        if (iterable.size() - start < r - current.size()) {
            return;
        }

        // start부터 끝까지 각 요소를 시도
        for (int i = start; i < iterable.size(); i++) {
            current.add(iterable.get(i));
            generateCombinations(iterable, r, i + 1, current, result);
            current.remove(iterable.get(i));
        }
    }
}

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N, M, V;
    final Map<Integer, Set<Integer>> graph = new HashMap<>();

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            var _vnm = reader.readInts();
            this.N = _vnm[0];
            this.M = _vnm[1];
            this.V = _vnm[2];
            reader.lines().forEach(line -> {
                var parts = line.split(" ");
                int source = Integer.parseInt(parts[0]);
                int dest = Integer.parseInt(parts[1]);
                graph.computeIfAbsent(source, k -> new TreeSet<>()).add(dest);
                graph.computeIfAbsent(dest, k -> new TreeSet<>()).add(source);
            });

            for (Integer key : graph.keySet()) {
            }
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
    public void run() {
        {
            boolean[] visited = new boolean[N + 1];
            dfs(V, visited);
        }
        sb.append("\n");
        {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 1];
            queue.add(V);

            while (!queue.isEmpty()) {
                int v = queue.poll();
                if (visited[v])
                    continue;
                bfs(v, visited, queue);
            }
        }
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        sb.append(v).append(" ");

        Iterator<Integer> neighbors = graph.getOrDefault(v, new TreeSet<>()).iterator();
        if (!neighbors.hasNext()) {
            return;
        }
        while (neighbors.hasNext()) {
            int neighbor = neighbors.next();
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    private void bfs(int v, boolean[] visited, Queue<Integer> queue) {
        visited[v] = true;
        sb.append(v).append(" ");

        Iterator<Integer> neighbors = graph.getOrDefault(v, new TreeSet<>()).iterator();
        if (!neighbors.hasNext()) {
            return;
        }
        while (neighbors.hasNext()) {
            queue.add(neighbors.next());
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
        return Arrays.stream(readInts()).boxed().collect(Collectors.toList());
    }

    @Override
    public List<String> lines() throws IOException {
        return br.lines().collect(Collectors.toList());
    }
}

interface IAbilityBoard {
    int absScoreDiff(boolean[] teamCase);
}

interface IRunner {
    void run();

    void flush();
}

interface IReader {
    void skipLine() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;

    List<String> lines() throws IOException;
}
