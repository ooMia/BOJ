import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
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

class Runner implements IRunner {
    final IReader reader;
    final BufferedWriter bw;
    final StringBuilder sb = new StringBuilder();

    final int N;
    final IAbilityBoard board;
    int min = Integer.MAX_VALUE;

    Runner(BufferedReader br, BufferedWriter bw) {
        this.reader = new Reader(br);
        this.bw = bw;

        try {
            this.N = reader.readInts()[0];
            int[][] S = new int[N][N];
            for (int i = 0; i < N; ++i) {
                S[i] = reader.readInts();
            }
            this.board = new AbilityBoard(N, S);
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
        boolean[] teamCase = new boolean[N];

        final int pivot = 0;
        teamCase[pivot] = true;

        dfs(1, pivot, teamCase);
        sb.append(min).append('\n');
    }

    private void dfs(int size, int lastAddedIndex, boolean[] isTeam) {
        if (size == N / 2) {
            this.min = Math.min(this.min, board.absScoreDiff(isTeam));
            return;
        }

        var sizeNeeded = N / 2 - size;

        for (int i = lastAddedIndex + 1; i <= N - sizeNeeded; ++i) {
            isTeam[i] = true;
            dfs(size + 1, i, isTeam);
            isTeam[i] = false;
        }
    }
}

interface IRunner {
    void run();

    void flush();
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

interface IAbilityBoard {
    int absScoreDiff(boolean[] teamCase);
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

interface IReader {
    void skipLine() throws IOException;

    List<Integer> readIntegers() throws IOException;

    int[] readInts() throws IOException;

    List<String> lines() throws IOException;
}
